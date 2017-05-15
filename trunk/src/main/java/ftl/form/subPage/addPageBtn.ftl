				</ul>
<#list relObjects as object>
				<div class="zk-tab mt15" id="${object.objectName}Table"></div>
</#list>
				<ul>
	                <li>
	                    <label class="ser-label">&nbsp;</label>
	                    <div class="form-content clearfix">
	                        <button class="btn btn-orange" id="add${mainObject.className}"><i class="fa fa-save"></i>保存</button>
	                        	<button class="btn btn-default" id="home${mainObject.className}" type="button">
								<i class="fa fa-close mr5"></i>取消
							</button>
	                    </div>
	                </li>
				</ul>	
			</form>
		</div>
	</div>