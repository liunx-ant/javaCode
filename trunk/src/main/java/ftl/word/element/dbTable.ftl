					<w:p w:rsidR="00B25C29" w:rsidRDefault="0075435A" w:rsidP="006E48E0">
						<w:pPr>
							<w:pStyle w:val="3" />
						</w:pPr>
						<w:bookmarkStart w:id="17" w:name="_Toc459383980" />
						<w:r>
							<w:rPr>
								<w:rFonts w:hint="eastAsia" />
							</w:rPr>
							<w:t>6.2.${dbTableIndex}、</w:t>
						</w:r>
						<w:r w:rsidR="00EF71A5">
							<w:rPr>
								<w:rFonts w:hint="eastAsia" />
							</w:rPr>
							<w:t>${object.tableName}（${object.title}）</w:t>
						</w:r>
						<w:bookmarkEnd w:id="17" />
					</w:p>
				
					<w:tbl>
						<w:tblPr>
							<w:tblStyle w:val="a6" />
							<w:tblW w:w="8613" w:type="dxa" />
							<w:tblLook w:val="04A0" />
						</w:tblPr>
						<w:tblGrid>
							<w:gridCol w:w="1994" />
							<w:gridCol w:w="6619" />
						</w:tblGrid>
						<w:tr w:rsidR="00610FD7" w:rsidTr="00E934E6">
							<w:tc>
								<w:tcPr>
									<w:tcW w:w="1994" w:type="dxa" />
								</w:tcPr>
								<w:p w:rsidR="00610FD7" w:rsidRDefault="00EF71A5" w:rsidP="00E934E6">
									<w:pPr>
										<w:pStyle w:val="a7" />
										<w:jc w:val="center" />
									</w:pPr>
									<w:r>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
										</w:rPr>
										<w:t>字段</w:t>
									</w:r>
								</w:p>
							</w:tc>
							<w:tc>
								<w:tcPr>
									<w:tcW w:w="1994" w:type="dxa" />
								</w:tcPr>
								<w:p w:rsidR="00610FD7" w:rsidRDefault="00EF71A5" w:rsidP="00E934E6">
									<w:pPr>
										<w:pStyle w:val="a7" />
										<w:jc w:val="center" />
									</w:pPr>
									<w:r>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
										</w:rPr>
										<w:t>类型</w:t>
									</w:r>
								</w:p>
							</w:tc>
							<w:tc>
								<w:tcPr>
									<w:tcW w:w="1994" w:type="dxa" />
								</w:tcPr>
								<w:p w:rsidR="00610FD7" w:rsidRDefault="00EF71A5" w:rsidP="00E934E6">
									<w:pPr>
										<w:pStyle w:val="a7" />
										<w:jc w:val="center" />
									</w:pPr>
									<w:r>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
										</w:rPr>
										<w:t>长度</w:t>
									</w:r>
								</w:p>
							</w:tc>
							<w:tc>
								<w:tcPr>
									<w:tcW w:w="6619" w:type="dxa" />
								</w:tcPr>
								<w:p w:rsidR="00610FD7" w:rsidRDefault="00EF71A5" w:rsidP="00E934E6">
									<w:pPr>
										<w:pStyle w:val="a7" />
										<w:jc w:val="center" />
									</w:pPr>
									<w:r>
										<w:rPr>
											<w:rFonts w:hint="eastAsia" />
										</w:rPr>
										<w:t>说明</w:t>
									</w:r>
								</w:p>
							</w:tc>
						</w:tr>

	<#list object.properties as property>
		<#include "/element/dbTr.ftl"/>
	</#list>

						
						
						
					</w:tbl>