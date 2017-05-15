					
					<w:p w:rsidR="00A938B7" w:rsidRDefault="00E6052E" w:rsidP="00456716">
						<w:pPr>
							<w:pStyle w:val="4" />
							<w:rPr>
								<w:rFonts w:hint="eastAsia" />
							</w:rPr>
						</w:pPr>
						<w:r>
							<w:rPr>
								<w:rFonts w:hint="eastAsia" />
							</w:rPr>
							<w:t>4</w:t>
						</w:r>
						<w:r w:rsidR="00CF0E04">
							<w:rPr>
								<w:rFonts w:hint="eastAsia" />
							</w:rPr>
							<w:t>.${wordApiProtocolIndex}.2.${methodIndex}、</w:t>
						</w:r>
						<w:r w:rsidR="006101DF" w:rsidRPr="002401EE">
							<w:rPr>
								<w:rFonts w:hint="eastAsia" />
							</w:rPr>
							<w:t>${wordApiMethod.desc}</w:t>
						</w:r>
					</w:p>
					
					
<#include "/element/methodDesc.ftl"/>
<#include "/element/methodDefinition.ftl"/>		
<#include "/element/methodParams.ftl"/>
<#include "/element/methodReponse.ftl"/> 
