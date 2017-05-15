					<w:p w:rsidR="0032551F" w:rsidRDefault="00EF6109" w:rsidP="00EF6109">
						<w:pPr>
							<w:pStyle w:val="2" />
						</w:pPr>
						<w:bookmarkStart w:id="13" w:name="_Toc459383976" />
						<w:r>
							<w:rPr>
								<w:rFonts w:hint="eastAsia" />
							</w:rPr>
							<w:t>4.2、</w:t>
						</w:r>
						<w:r w:rsidR="0032551F">
							<w:rPr>
								<w:rFonts w:hint="eastAsia" />
							</w:rPr>
							<w:t>类说明</w:t>
						</w:r>
						<w:bookmarkEnd w:id="13" />
					</w:p>
					
					<w:tbl>
						<w:tblPr>
							<w:tblStyle w:val="a6" />
							<w:tblW w:w="8613" w:type="dxa" />
							<w:tblLayout w:type="fixed" />
							<w:tblLook w:val="04A0" />
						</w:tblPr>
						<w:tblGrid>
							<w:gridCol w:w="1951" />
							<w:gridCol w:w="6662" />
						</w:tblGrid>
						<w:tr w:rsidR="00D21FD1" w:rsidTr="00D15DEF">
							<w:tc>
								<w:tcPr>
									<w:tcW w:w="1951" w:type="dxa" />
								</w:tcPr>
								<w:p w:rsidR="00D21FD1" w:rsidRDefault="00D21FD1" w:rsidP="00E934E6">
									<w:pPr>
										<w:pStyle w:val="a7" />
										<w:jc w:val="center" />
									</w:pPr>
									<w:r>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
										</w:rPr>
										<w:t>类名</w:t>
									</w:r>
								</w:p>
							</w:tc>
							<w:tc>
								<w:tcPr>
									<w:tcW w:w="6662" w:type="dxa" />
								</w:tcPr>
								<w:p w:rsidR="00D21FD1" w:rsidRDefault="00D21FD1" w:rsidP="00E934E6">
									<w:pPr>
										<w:pStyle w:val="a7" />
										<w:jc w:val="center" />
									</w:pPr>
									<w:r>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
										</w:rPr>
										<w:t>功能说明</w:t>
									</w:r>
								</w:p>
							</w:tc>
						</w:tr>
<#list controllerCodeNoteClasses as object>
	<#include "/element/controllerClassTr.ftl"/>
</#list>
					</w:tbl>