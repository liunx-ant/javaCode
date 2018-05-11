package  ${mainObject.apiPackageName}.consumer.api;

import java.util.List;

import com.foriseland.fjf.rpc.storage.PageRequest;
import com.foriseland.fjf.rpc.storage.PageResponse;
import com.foriseland.fjf.rpc.storage.Request;
import com.foriseland.fjf.rpc.storage.Response;
import ${mainObject.apiPackageName}.consumer.dto.${mainObject.className}Dto;
import ${mainObject.apiPackageName}.consumer.vo.${mainObject.className}Vo;

/**
 * 
  * ${mainObject.title}Api
  * @author ${author}
  * @version ${version}
  * @date ${nowDate}
  * Copyright ${copyright}
 */
public interface I${mainObject.className}Api {
    
	/**
	 * 新增${mainObject.title}
	 * @param ${mainObject.title}
	 * @return ResultMessage 对象
	 * @author ${author}
	 * @date ${nowDate}
	 */
	public Response<Integer> save(Request<${mainObject.className}Dto> req);

	/**
	 * 修改${mainObject.title}
	 * @param ${mainObject.title}
	 * @return ResultMessage 对象
	 * @author ${author}
	 * @date ${nowDate}
	 */
	public Response<Integer> update(Request<${mainObject.className}Dto> req);
	
	/**
	 * 根据id查询${mainObject.title}
	 * @param id
	 * @return ${mainObject.title}
	 * @author ${author}
	 * @date ${nowDate}
	 */
	public Response<${mainObject.className}Vo> queryById(Request<Long> req);
	
	/**
	 * 根据${mainObject.title}查询${mainObject.title}
	 * @param ${mainObject.title}
	 * @return ${mainObject.title}
	 * @author ${author}
	 * @date ${nowDate}
	 */
	public Response<${mainObject.className}Vo> queryByInfo(Request<${mainObject.className}Dto> req);
	
	/**
	 * 查询${mainObject.title}(单表)
	 * @param ${mainObject.title}
	 * @return ${mainObject.title}集合
	 * @author ${author}
	 * @date ${nowDate}
	 */
    public Response<List<${mainObject.className}Vo>> queryAll(Request<${mainObject.className}Dto> req);
	/**
	 * 分页查询${mainObject.title}(单表)
	 * @param start 开始记录,size每页记录, ${mainObject.title}
	 * @return ${mainObject.title}集合
	 * @author ${author}
	 * @date ${nowDate}
	 */
    public PageResponse<List<${mainObject.className}Vo>> queryAll(PageRequest<${mainObject.className}Dto> req);
    
}