						<w:tr w:rsidR="00610FD7" w:rsidTr="00E23FE1">
							<w:tc>
								<w:tcPr>
									<w:tcW w:w="1951" w:type="dxa" />
								</w:tcPr>
								<w:p w:rsidR="00610FD7" w:rsidRDefault="00610FD7" w:rsidP="00E934E6">
									<w:pPr>
										<w:pStyle w:val="a7" />
									</w:pPr>
									<w:r>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
										</w:rPr>
										<w:t>${object.name}</w:t>
									</w:r>
								</w:p>
							</w:tc>
							<w:tc>
								<w:tcPr>
									<w:tcW w:w="6662" w:type="dxa" />
								</w:tcPr>
								<w:p w:rsidR="00610FD7" w:rsidRDefault="00610FD7" w:rsidP="00E934E6">
									<w:pPr>
										<w:pStyle w:val="a7" />
									</w:pPr>
									<w:r>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
										</w:rPr>
										<w:t>${object.describe}，主要实现了以下方法：</w:t>
									</w:r>
								</w:p>
<#list object.codeNoteMethods as codeNoteMethod>
		<w:p w:rsidR="00610FD7" w:rsidRDefault="00610FD7" w:rsidP="00E934E6">
									<w:pPr>
										<w:pStyle w:val="a7" />
									</w:pPr>
									<w:r>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
										</w:rPr>
										<w:t>
										${codeNoteMethod_index+1}、${codeNoteMethod.describe}<#if (object.codeNoteMethods?size==codeNoteMethod_index+1) >。<#else>；</#if>
										</w:t>
									</w:r>
								</w:p>
</#list>
							</w:tc>
						</w:tr>