				</ul>
<#list relObjects as object>
				<div class="zk-tab mt15" id="${object.objectName}Table"></div>
</#list>
				<ul>
	                <li>
	                    <label class="ser-label">&nbsp;</label>
	                    <div class="form-content clearfix">
	                		<input type="hidden" id="id" value="${r'$'}{id}">
	                         <button class="btn btn-default" id="home${mainObject.className}" type="button">
									<i class="fa fa-reply mr5"></i>返回
							</button>
	                    </div>
	                </li>
				</ul>
			</form>
		</div>
	</div>