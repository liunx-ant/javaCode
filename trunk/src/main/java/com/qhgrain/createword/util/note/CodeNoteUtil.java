package com.qhgrain.createword.util.note;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.qhgrain.common.util.BeanUtil;
import com.qhgrain.createword.config.Config;
import com.qhgrain.createword.util.CharDealUtil;

/**
 * 注释解析处理
 * 
 * @author duweili
 *
 */
public class CodeNoteUtil {
	/**
	 * 获得codeNoteClass列表的map列表
	 * 
	 * @param codeNoteClasses
	 * @return
	 */
	public static List<Map> getCodeNoteClassMaps(List<CodeNoteClass> codeNoteClasses) {
		List<Map> maps = new ArrayList();
		for (CodeNoteClass codeNoteClass : codeNoteClasses) {
			maps.add(codeNoteClassToMap(codeNoteClass));
		}
		return maps;
	}

	/**
	 * 将codeNoteClass转换为oMap
	 * 
	 * @param codeNoteClass
	 * @return
	 */
	private static Map<String, Object> codeNoteClassToMap(CodeNoteClass codeNoteClass) {
		Map<String, Object> codeNoteClassMap = BeanUtil.objectToMap(codeNoteClass);
		// propertyMaps
		List<CodeNoteMethod> codeNoteMethods = codeNoteClass.getCodeNoteMethods();
		List<Map> codeNoteMethodMaps = new ArrayList<Map>();
		for (int i = 0; i < codeNoteMethods.size(); i++) {
			// propertyMap
			CodeNoteMethod codeNoteMethod = codeNoteMethods.get(i);
			Map codeNoteMethodMap = BeanUtil.objectToMap(codeNoteMethod);
			codeNoteMethodMaps.add(codeNoteMethodMap);
		}
		codeNoteClassMap.put("codeNoteMethods", codeNoteMethods);

		return codeNoteClassMap;
	}

	/**
	 * 获得文件列表对用的CodeNoteClasse
	 * 
	 * @param filepath
	 * @param filter
	 * @return
	 * @throws Exception
	 */

	public static List<CodeNoteClass> getCodeNoteClasses(List<String> filepaths, String[] filter) throws Exception {
		List<CodeNoteClass> codeNoteClassList = new ArrayList<CodeNoteClass>();
		for (String filepath : filepaths) {
			codeNoteClassList.addAll(getCodeNoteClass(filepath, filter));
		}
		return clearRepetitionPath(codeNoteClassList);
	}

	/**
	 * 获得文件列表对用的CodeNoteClasse
	 * 
	 * @param filepath
	 * @param filter
	 * @return
	 * @throws Exception
	 */

	public static List<CodeNoteClass> getCodeNoteClass(String filepath, String[] filter) throws Exception {
		List<File> files = getFiles(filepath, filter);
		List<CodeNoteClass> codeNoteClassList = new ArrayList<CodeNoteClass>();
		for (File file : files) {
			codeNoteClassList.add(getCodeNoteClass(file));
		}
		return codeNoteClassList;
	}

	/**
	 * 获得实体文件列表对用的CodeNoteClasse
	 * 
	 * @param filepath
	 * @param filter
	 * @return
	 * @throws Exception
	 */

	public static List<CodeNoteClass> getEntityCodeNoteClasses(List<String> filepaths, String[] filter) throws Exception {
		List<CodeNoteClass> codeNoteClassList = new ArrayList<CodeNoteClass>();
		for (String filepath : filepaths) {
			codeNoteClassList.addAll(getEntityCodeNoteClass(filepath, filter));
		}
		return clearRepetitionPath(codeNoteClassList);
	}

	/**
	 * 获得实体文件列表对用的CodeNoteClasse
	 * 
	 * @param filepath
	 * @param filter
	 * @return
	 * @throws Exception
	 */

	public static List<CodeNoteClass> getEntityCodeNoteClass(String filepath, String[] filter) throws Exception {
		List<File> files = getFiles(filepath, filter);
		List<CodeNoteClass> codeNoteClassList = new ArrayList<CodeNoteClass>();
		for (File file : files) {
			codeNoteClassList.add(getEntityCodeNoteClass(file));
		}
		return codeNoteClassList;
	}

	/**
	 * 获得JS文件列表对用的CodeNoteClasse
	 * 
	 * @param filepath
	 * @param filter
	 * @return
	 * @throws Exception
	 */

	public static List<CodeNoteClass> getJsCodeNoteClasses(List<String> filepaths, String[] filter) throws Exception {
		List<CodeNoteClass> codeNoteClassList = new ArrayList<CodeNoteClass>();
		for (String filepath : filepaths) {
			codeNoteClassList.addAll(getJsCodeNoteClass(filepath, filter));
		}
		return clearRepetitionPath(codeNoteClassList);
	}

	/**
	 * 清除重复
	 * 
	 * @param codeNoteClassList
	 */

	private static List<CodeNoteClass> clearRepetitionPath(List<CodeNoteClass> codeNoteClassList) {
		List<CodeNoteClass> noRepetitionList = new ArrayList<CodeNoteClass>();
		for (CodeNoteClass codeNoteClass : codeNoteClassList) {
			String file = codeNoteClass.getPath();
			if (!isRepetitionPath(file, noRepetitionList)) {
				noRepetitionList.add(codeNoteClass);
			}
		}
		return noRepetitionList;
	}

	/**
	 * 是否为重复的路径
	 * 
	 * @param codeNoteClassList
	 * @param noRepetitionList
	 * @return
	 */
	private static boolean isRepetitionPath(String filePath, List<CodeNoteClass> noRepetitionList) {
		boolean isRepetitionPath = false;
		for (CodeNoteClass codeNoteClass : noRepetitionList) {
			if (filePath.equals(codeNoteClass.getPath())) {
				isRepetitionPath = true;
				break;
			}
		}
		return isRepetitionPath;
	}

	/**
	 * 获得JS文件列表对用的CodeNoteClasse
	 * 
	 * @param filepath
	 * @param filter
	 * @return
	 * @throws Exception
	 */

	public static List<CodeNoteClass> getJsCodeNoteClass(String filepath, String[] filter) throws Exception {
		List<File> files = getFiles(filepath, filter);
		List<CodeNoteClass> codeNoteClassList = new ArrayList<CodeNoteClass>();
		for (File file : files) {
			System.out.println("读取JS文件" + file.getName() + "中。。。");
			codeNoteClassList.add(getJsCodeNoteClass(file));
			System.out.println("读取JS文件" + file.getName() + "成功！");
		}
		return codeNoteClassList;
	}

	/**
	 * 获得文件列表
	 * 
	 * @param filepath
	 * @param filter
	 * @return
	 */
	private static List<File> getFiles(String filepath, String[] filter) {
		List<File> files = new ArrayList<File>();
		getFiles(filepath, files, filter);
		return files;
	}

	/**
	 * 是否存在于忽略列表
	 * 
	 * @param file
	 * @return
	 */

	private static boolean isInNoteFileList(File file, String[] filter) {
		boolean isIn = false;
		String[] pathParts = file.getName().split(System.getProperties().getProperty("line.separator"));
		String name = pathParts[pathParts.length - 1];
		for (String fileName : filter) {
			if (name.equals(fileName)) {
				isIn = true;
				break;
			} else {
				if (fileName.split("\\*").length > 1) {
					if (name.endsWith(fileName.split("\\*")[fileName.split("\\*").length - 1])) {
						isIn = true;
						break;
					}
				}
			}
		}
		return isIn;
	}

	/**
	 * 是否存在于忽略列表
	 * 
	 * @param file
	 * @return
	 */

	private static boolean isInIgoreFileList(File file) {
		boolean isIn = false;
		String[] pathParts = file.getName().split(System.getProperties().getProperty("line.separator"));
		String name = pathParts[pathParts.length - 1];
		for (String fileName : Config.IGNORE_FILE_LIST) {
			if (name.equals(fileName)) {
				isIn = true;
				break;
			} else {
				if (fileName.split("\\*").length > 1) {
					if (name.endsWith(fileName.split("\\*")[fileName.split("\\*").length - 1])) {
						isIn = true;
						break;
					}
				}
			}
		}
		return isIn;
	}

	/**
	 * 获得问题，并添加到fileList
	 * 
	 * @param filepath
	 * @param fileList
	 */
	private static void getFiles(String filepath, List<File> files, String[] filter) {
		File file = new File(filepath);
		if (!file.isDirectory()) {
			addToFileList(files, file, filter);
		} else if (file.isDirectory()) {
			String[] oneFilelist = file.list();
			for (int i = 0; i < oneFilelist.length; i++) {
				File readfile = new File(filepath + "\\" + oneFilelist[i]);
				if (!readfile.isDirectory()) {
					addToFileList(files, readfile, filter);
				} else if (readfile.isDirectory()) {
					getFiles(filepath + "\\" + oneFilelist[i], files, filter);
				}
			}
		}
	}

	/**
	 * 添加到fileList
	 * 
	 * @param fileList
	 * @param file
	 */
	private static void addToFileList(List<File> fileList, File file, String[] filter) {
		if (isInNoteFileList(file, filter) && !isInIgoreFileList(file)) {
			fileList.add(file);
		}
	}

	/**
	 * 获得方法名或者类名
	 * 
	 * @param defined
	 * @return
	 */
	private static String getCodeNoteJavaName(String defined) {
		String name = "";
		String definedParts[] = defined.split(" ");
		int nameIndex = 2;
		for (int i = 0; i < definedParts.length; i++) {
			String definedPart = definedParts[i];
			if (definedPart.equals("")) {
				continue;
			} else {
				if (definedPart.equals("final") || definedPart.equals("static") || definedPart.equals("abstract")) {
					nameIndex++;
				}
				if (nameIndex == i) {
					name = definedPart.replace("(", "");
				}
			}
		}
		return CharDealUtil.xmlCharDeal(name);
	}

	/**
	 * 获得注释的名称
	 * 
	 * @param defined
	 * @return
	 */
	private static String getCodeNoteDescribe(String note) {
		boolean isDescribe = true;
		String describe = "";
		for (String n : note.split("\\*")) {
			n = n.replace(" ", "");
			if (!n.equals("") && !n.equals("/") && !n.equals("\\")) {
				if (n.split("@").length > 1) {
					isDescribe = false;
					n = n.replaceAll(" ", "");
					break;
				} else {
					if (isDescribe) {
						describe += n;// 获得类描述
					}
				}
			}
		}
		return CharDealUtil.xmlCharDeal(describe);
	}

	/**
	 * 获得jsCodeNoteMethod
	 * 
	 * @param methodDefined
	 *            方法信息
	 * @param note
	 * @return CodeNoteMethod
	 * @throws Exception
	 */

	private static CodeNoteMethod getJsCodeNoteMethod(String defined, String note) throws Exception {
		CodeNoteMethod codeNoteMethod = new CodeNoteMethod();
		codeNoteMethod.setDescribe(getCodeNoteDescribe(note));
		codeNoteMethod.setName(getJsCodeNoteName(defined));
		return codeNoteMethod;
	}

	/**
	 * 获得方法名
	 * 
	 * @param defined
	 * @return
	 */
	private static String getJsCodeNoteName(String defined) {
		String name = "";
		String definedParts[] = defined.split(" ");
		if (definedParts.length > 1) {
			name = definedParts[1].split("\\(")[0];
		}
		return CharDealUtil.xmlCharDeal(name);
	}

	/**
	 * 获得CodeNoteMethod
	 * 
	 * @param methodDefined
	 *            方法信息
	 * @param note
	 * @return CodeNoteMethod
	 * @throws Exception
	 */

	private static CodeNoteMethod getCodeNoteMethod(String defined, String note) throws Exception {
		CodeNoteMethod codeNoteMethod = new CodeNoteMethod();
		codeNoteMethod.setDescribe(getCodeNoteDescribe(note));
		codeNoteMethod.setName(getCodeNoteJavaName(defined));
		return codeNoteMethod;
	}

	/**
	 * 获得CodeNoteClass
	 * 
	 * @param methodDefined
	 *            方法信息
	 * @param note
	 * @return CodeNoteMethod
	 * @throws Exception
	 */
	private static void setCodeNoteClass(String defined, String note, CodeNoteClass codeNoteClass) {
		codeNoteClass.setName(codeNoteClass.getPackageName() + "." + getCodeNoteJavaName(defined) + "");
		codeNoteClass.setDescribe(getCodeNoteDescribe(note));
	}

	/**
	 * 获得备注信息
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private static CodeNoteClass getCodeNoteClass(File file) throws Exception {
		FileInputStream is = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader bf = new BufferedReader(isr);
		CodeNoteClass codeNoteClass = new CodeNoteClass();
		codeNoteClass.setPath(file.getPath());
		String context = null;
		StringBuffer sb = null;
		List<String> list = new ArrayList<String>();
		List<CodeNoteMethod> codeNoteMethods = new ArrayList<CodeNoteMethod>();
		boolean isClassNote = true;
		boolean isRowsNote = false;
		int methodCount = 0;
		do {
			context = bf.readLine();
			if (context == null) {
				break;
			}
			if (context.trim().equals("")) {
				continue;
			}
			// 去除tap键
			context = context.replaceAll("\t", "");
			// 去除开头的空格键
			context = clearFrontSpace(context);
			if (context.startsWith("package")) {
				codeNoteClass.setPackageName(context.replace("package ", "").replace(";", ""));
			}
			if (isClassNote) {
				if (context.replace("static ", "").replace("final ", "").replace("abstract ", "").startsWith("public class") || context.replace("static ", "").replace("final ", "").replace("abstract ", "").startsWith("public interface")) {
					if (list.size() == 0) {
						throw new Exception("未检索到类" + context + "的注释信息，请补全！");
					} else {
						setCodeNoteClass(context, list.get(0), codeNoteClass);
						isClassNote = false;
						System.out.println("类[" + codeNoteClass.getName() + "]注释[" + codeNoteClass.getDescribe() + "]");
					}
				}
			} else {
				if ((context.startsWith("private")) && (context.endsWith("{") || context.endsWith(");")) && (context.endsWith("{") || context.split("=").length == 1)) {
					methodCount++;
					if (list.size() - 1 == methodCount) {
						CodeNoteMethod codeNoteMethod = getCodeNoteMethod(context, list.get(methodCount));
						System.out.println("方法[" + codeNoteMethod.getName() + "]注释[" + codeNoteMethod.getDescribe() + "]");
					} else {
						throw new Exception("未检索到" + codeNoteClass.getName() + "的方法" + context + "的注释信息，请补全！");
					}
				}

				if ((context.startsWith("protected")) && (context.endsWith("{") || context.endsWith(");")) && (context.endsWith("{") || context.split("=").length == 1)) {
					methodCount++;
					if (list.size() - 1 == methodCount) {
						CodeNoteMethod codeNoteMethod = getCodeNoteMethod(context, list.get(methodCount));
						System.out.println("方法[" + codeNoteMethod.getName() + "]注释[" + codeNoteMethod.getDescribe() + "]");
					} else {
						throw new Exception("未检索到" + codeNoteClass.getName() + "的方法" + context + "的注释信息，请补全！");
					}
				}
				if ((context.startsWith("public")) && (context.endsWith("{") || context.endsWith(");")) && (context.endsWith("{") || context.split("=").length == 1)) {
					methodCount++;
					if (list.size() - 1 == methodCount) {
						CodeNoteMethod codeNoteMethod = getCodeNoteMethod(context, list.get(methodCount));
						codeNoteMethods.add(codeNoteMethod);
						System.out.println("方法[" + codeNoteMethod.getName() + "]注释[" + codeNoteMethod.getDescribe() + "]");
					} else {
						throw new Exception("未检索到" + codeNoteClass.getName() + "的方法" + context + "的注释信息，请补全！");
					}
				}
			}
			if (context.trim().startsWith("//")) {
				// list.add(context.trim());
				continue;
			} else if (context.trim().startsWith("/**")) {
				sb = new StringBuffer();
				sb.append(context);
			} else if (context.trim().equals("/*")) {
				isRowsNote = true;
			} else if (context.trim().startsWith("*/")) {
				if (isRowsNote) {
					isRowsNote = false;
				} else {
					sb.append(context);
					list.add(sb.toString());
				}
			} else if (context.trim().startsWith("*")) {
				sb.append(context);
			}
		} while (context != null);
		codeNoteClass.setCodeNoteMethods(codeNoteMethods);
		return codeNoteClass;
	}

	/**
	 * 获得实体类备注信息
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private static CodeNoteClass getEntityCodeNoteClass(File file) throws Exception {
		FileInputStream is = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader bf = new BufferedReader(isr);
		CodeNoteClass codeNoteClass = new CodeNoteClass();
		codeNoteClass.setPath(file.getPath());
		String context = null;
		StringBuffer sb = null;
		List<String> list = new ArrayList<String>();
		List<CodeNoteMethod> codeNoteMethods = new ArrayList<CodeNoteMethod>();
		boolean isClassNote = true;
		boolean isRowsNote = false;
		int methodCount = 0;
		do {
			context = bf.readLine();
			if (context == null) {
				break;
			}
			if (context.trim().equals("")) {
				continue;
			}
			// 去除tap键
			context = context.replaceAll("\t", "");
			// 去除开头的空格键
			context = clearFrontSpace(context);

			if (context.trim().split("//").length > 1&&!context.trim().startsWith("//")) {
				list.add(context.trim().split("//")[context.trim().split("//").length - 1]);
			}
			if (context.startsWith("package")) {
				codeNoteClass.setPackageName(context.replace("package ", "").replace(";", ""));
			}
			if (isClassNote) {
				if (context.replace("static ", "").replace("final ", "").replace("abstract ", "").startsWith("public class") || context.replace("static ", "").replace("final ", "").replace("abstract ", "").startsWith("public interface")) {
					if (list.size() == 0) {
						throw new Exception("未检索到类" + context + "的注释信息，请补全！");
					} else {
						setCodeNoteClass(context, list.get(0), codeNoteClass);
						isClassNote = false;
						System.out.println("实体类[" + codeNoteClass.getName() + "]注释[" + codeNoteClass.getDescribe() + "]");
					}
				}
			} else {

				// 获得属性的备注信息
				if (context.startsWith("private")) {
					if (context.trim().split("//").length > 1) {
						context = context.trim().split("//")[0];
					}
					context = context.replace(";", "");
					methodCount++;
					if (list.size() - 1 == methodCount) {
						CodeNoteMethod codeNoteMethod = getCodeNoteMethod(context, list.get(methodCount));
						codeNoteMethods.add(codeNoteMethod);
						System.out.println("实体类属性___[" + codeNoteMethod.getName() + "]____[" + codeNoteMethod.getDescribe() + "]");
					} else {
						if (context.split("serialVersionUID").length == 1) {
							throw new Exception("未检索到" + codeNoteClass.getName() + "的属性" + context + "的注释信息，请补全！");
						} else {
							methodCount--;
						}
					}
				}
				
				// 获得属性的备注信息
                if (context.startsWith("public")&&!context.endsWith("{")) {
                    if (context.trim().split("//").length > 1) {
                        context = context.trim().split("//")[0];
                    }
                    if (context.trim().split("=").length > 1) {
                        context = context.trim().split("=")[0];
                    }
                    context = context.replace(";", "");
                    context= context.replace("static ", "").replace("final ", "").replace("abstract ", "");
                    methodCount++;
                    if (list.size() - 1 == methodCount) {
                        CodeNoteMethod codeNoteMethod = getCodeNoteMethod(context, list.get(methodCount));
                        codeNoteMethods.add(codeNoteMethod);
                        System.out.println("实体类属性___[" + codeNoteMethod.getName() + "]____[" + codeNoteMethod.getDescribe() + "]");
                    } else {
                        if (context.split("serialVersionUID").length == 1) {
                            throw new Exception("未检索到" + codeNoteClass.getName() + "的属性" + context + "的注释信息，请补全！");
                        } else {
                            methodCount--;
                        }
                    }
                }
			}
			if (context.trim().startsWith("//")) {
				list.add(context.trim());
				continue;
			} else if (context.trim().startsWith("/**")) {
				sb = new StringBuffer();
				sb.append(context);
			} else if (context.trim().equals("/*")) {
				isRowsNote = true;
			} else if (context.trim().startsWith("*/")) {
				if (isRowsNote) {
					isRowsNote = false;
				} else {
					sb.append(context);
					list.add(sb.toString());
				}
			} else if (context.trim().startsWith("*")) {
				sb.append(context);
			}
		} while (context != null);

		codeNoteClass.setCodeNoteMethods(codeNoteMethods);
		return codeNoteClass;
	}

	/**
	 * 获得备注信息
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private static CodeNoteClass getJsCodeNoteClass(File file) throws Exception {
		FileInputStream is = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader bf = new BufferedReader(isr);
		CodeNoteClass codeNoteClass = new CodeNoteClass();
		codeNoteClass.setPath(file.getPath());
		String context = null;
		StringBuffer sb = null;
		List<String> list = new ArrayList<String>();
		List<CodeNoteMethod> codeNoteMethods = new ArrayList<CodeNoteMethod>();
		boolean isRowsNote = false;
		boolean isJsNote = true;
		int methodCount = 0;
		codeNoteClass.setName(file.getName());
		do {
			context = bf.readLine();
			if (context == null) {
				break;
			}
			if (context.trim().equals("")) {
				continue;
			}
			// 去除tap键
			context = context.replaceAll("\t", "");
			// 去除开头的空格键
			context = clearFrontSpace(context);
			if (list.size() == 1 && isJsNote) {
				codeNoteClass.setDescribe(getCodeNoteDescribe(list.get(0)));
				System.out.println("JS[" + codeNoteClass.getName() + "]注释[" + codeNoteClass.getDescribe() + "]");
				isJsNote = false;
			}

			if (context.startsWith("function") && context.endsWith("{") && !context.startsWith("function(")) {
				if (list.size() - 2 == methodCount) {
					CodeNoteMethod codeNoteMethod = getJsCodeNoteMethod(context, list.get(methodCount + 1));
					codeNoteMethods.add(codeNoteMethod);
					System.out.println("方法[" + codeNoteMethod.getName() + "]注释[" + codeNoteMethod.getDescribe() + "]");
				} else {
					throw new Exception("未检索到" + codeNoteClass.getName() + "的方法" + context + "的注释信息，请补全！");
				}
				methodCount++;
			}
			if (context.trim().startsWith("//")) {
				// list.add(context.trim());
				continue;
			} else if (context.trim().startsWith("/**")) {
				sb = new StringBuffer();
				sb.append(context);
			} else if (context.trim().equals("/*")) {
				isRowsNote = true;
			} else if (context.trim().startsWith("*/")) {
				if (isRowsNote) {
					isRowsNote = false;
				} else {
					if (sb != null) {
						sb.append(context);
						list.add(sb.toString());
					}
				}
			} else if (context.trim().startsWith("*")) {
				if (sb != null) {
					sb.append(context);
				}
			}
		} while (context != null);

		if (list.size() == 0) {
			throw new Exception("未检索到JS" + codeNoteClass.getName() + "的注释信息，请补全！");
		}

		codeNoteClass.setCodeNoteMethods(codeNoteMethods);
		return codeNoteClass;
	}

	/**
	 * 清除字符串前面所有的空格
	 * 
	 * @param s
	 */
	private static String clearFrontSpace(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (i < s.length() - 1 && !s.substring(i, i + 1).equals(" ") && !s.substring(i, i + 1).equals("")) {
				return s.substring(i);
			}
		}
		return s;
	}
}
