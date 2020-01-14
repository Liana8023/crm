<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- 客户信息查询 -->
	<div id="customer">
		<div>
			<form class="form-inline">
				<div class="form-group">
					<input name="custName" type="text" class="form-control" id="exampleInputEmail3" placeholder="请输入客户名称">
				</div>
				<!-- <div class="form-group">
    <input type="text" class="form-control" id="exampleInputPassword3" placeholder="请输入客户经理">
  </div> -->
				<div class="form-group">
					<select name="city" id="" class="form-control">
						<option value="">请选择所属地区</option>
						<option value="华中">华中</option>
						<option value="华北">华北</option>
						<option value="华南">华南</option>
						<option value="华东">华东</option>
					</select>
				</div>
				<div class="form-group">
					<select name="level" id="" class="form-control">
						<option value="">请选择客户等级</option>
						<option value="普通客户">普通客户</option>
						<option value="大客户">大客户</option>
						<option value="重点开发客户">重点开发客户</option>
						<option value="合作伙伴">合作伙伴</option>
						<option value="战略合作伙伴">战略合作伙伴</option>
					</select>
				</div>
				<input class="btn btn-default search" type="button" value="查询">
				<input class="btn btn-default" type="reset" value="重置"> 
				<input class="btn btn-default" type="button" value="新增" id="add">
			</form>
		</div>
		<div>
			<table class="table table-hover">
				<thead>
					<tr style="background: #E8E8E8;">
						<th>序号</th>
						<th>客户名称</th>
						<th>客户经理</th>
						<th>所属地区</th>
						<th>客户等级</th>
						<th>操作</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${customerinfo.list }" var="customer">
						<tr>
							<td>${customer.custId }</td>
							<td>${customer.custName }</td>
							<td>${customer.custManagerName }</td>
							<td>${customer.custRegion }</td>
							<td>${customer.custLevelLabel}</td>
							<td class="test">
								<i value="${customer.custId}" class="layui-icon layui-icon-edit first" title="编辑客户信息"></i> 
								<i value="${customer.custId}" class="layui-icon layui-icon-username user" title="编辑联系人信息"></i>
								<i value="${customer.custId}" class="layui-icon layui-icon-read editRecord" title="编辑交往记录"></i>
								<i value="${customer.custId}" class="layui-icon layui-icon-delete deletee" title="删除客户记录"></i>
								
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="fenye">
			<table class="footTable">
				<tr>
					<td colspan="5">共${customerinfo.total } 条记录 每页${customerinfo.size } 条
						第${customerinfo.pageNum } 页 <a href="javascript:void(0)"
						onclick="pageSelect(1)">首页</a> <c:choose>
							<c:when test="${customerinfo.hasPreviousPage }">
								<a href="javascript:void(0)"
									onclick="pageSelect(${customerinfo.prePage})">上一页</a>
							</c:when>
							<c:otherwise>
								<font color="#ABA8AB">上一页</font>
							</c:otherwise>
						</c:choose> <c:choose>
							<c:when test="${customerinfo.hasNextPage }">
								<a href="javascript:void(0)"
									onclick="pageSelect(${customerinfo.nextPage})">下一页</a>
							</c:when>
							<c:otherwise>
								<font color="#ABA8AB">下一页</font>
							</c:otherwise>
						</c:choose> <a href="javascript:void(0)"
						onclick="pageSelect(${customerinfo.lastPage })">尾页</a>
					</td>
				</tr>

			</table>
		</div>
		<!-- 删除模态框 -->
		<div class="modal" id="deleteModal">
			<div class="modal-dialog" style="width: 350px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">是否确定删除</h4>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default "
							data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary">确定</button>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 新增模态框 -->
		<div class="modal" id="toAdd">
			<div class="modal-dialog" style="height: 400px; overflow: auto;">
				<div class="modal-content">
					<form>
						<div class="modal-body">
							<div class="form-group">
								<label>客户姓名:</label> <input type="text" name="lmName"
									class="form-control">
							</div>
							<div class="form-group">
								<label>客户经理:</label> <input type="text" name="msName"
									class="form-control">
							</div>
							<div class="form-group">
								<label>所属地区:</label> <select name="region" id=""
									class="form-control">
									<option value="">请选择所属地区</option>
									<option value="华中">华中</option>
									<option value="华北">华北</option>
									<option value="华南">华南</option>
									<option value="华东">华东</option>
								</select>
							</div>
							<div class="form-group">
								<label>客户等级:</label> <select name="level" id=""
									class="form-control">
									<option value="">请选择客户等级</option>
									<option value="普通客户">普通客户</option>
									<option value="大客户">大客户</option>
									<option value="重点开发客户">重点开发客户</option>
									<option value="合作伙伴">合作伙伴</option>
									<option value="战略合作伙伴">战略合作伙伴</option>
								</select>
							</div>
							<div class="form-group">
								<label>客户地址:</label> <input type="text" name="addr"
									class="form-control">
							</div>
							<div class="form-group">
								<label>邮政编码:</label> <input type="text" name="zip"
									class="form-control">
							</div>
							<div class="form-group">
								<label>客户电话:</label> <input type="text" name="tel"
									class="form-control">
							</div>
							<div class="form-group">
								<label>客户传真:</label> <input type="text" name="fax"
									class="form-control">
							</div>
							<div class="form-group">
								<label>客户网址:</label> <input type="text" name="website"
									class="form-control">
							</div>
							<div class="form-group">
								<label>营业执照注册号:</label> <input type="text" name="licenceNo"
									class="form-control">
							</div>
							<div class="form-group">
								<label>法人:</label> <input type="text" name="chieftain"
									class="form-control">
							</div>
							<div class="form-group">
								<label>注册资金（万元）:</label> <input type="text" name="bankroll"
									class="form-control">
							</div>
							<div class="form-group">
								<label>年营业额:</label> <input type="text" name="turnover"
									class="form-control">
							</div>
							<div class="form-group">
								<label>开户银行:</label> <input type="text" name="bank"
									class="form-control">
							</div>
							<div class="form-group">
								<label>银行账号:</label> <input type="text" name="bankAccount"
									class="form-control">
							</div>
							<div class="form-group">
								<label>地税登记号:</label> <input type="text" name="localTaxNo"
									class="form-control">
							</div>
							<div class="form-group">
								<label>国税登记号:</label> <input type="text" name="nationalTaxNo"
									class="form-control">
							</div>
						</div>
					</form>
					<div class="modal-footer">
						<button class="btn" style="background: #ccc">取消</button>
						<button class="btn" style="background: #ccc">保存</button>
					</div>
				</div>
			</div>
		</div>
	</div>
<style>	
#customer {
	width: 1140px;
	overflow: hidden;
}

.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover,
	.pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover
	{
	background-color: #ccc;
	border-color: #ccc;
}

.pagination>li>a, .pagination>li>span {
	color: black;
}

.fenye {
	margin-left: 400px;
}

.form-inline {
	margin-top: 15px;
	margin-left: 10px;
	margin-bottom: 20px;
}

.search {
	margin-left: 50px;
}

.table th, td {
	text-align: center;
}

#add {
	margin-left: 250px;
}

#toAdd {
	margin-top: 50px;
}

#deleteModal {
	margin-top: 100px;
}
</style>
<script type="text/javascript">
	function pageSelect(curPage){
		$('.layui-body').load('customer/findAllCustomerByPage/'+curPage);
	};
	
	$(function(){
		var custId;
		//编辑交往记录
		$('.editRecord').on('click',function(){
			custId=$(this).attr("value");
			var url="activity/findActivitiesByCustId/"+custId+"/1";
			$('.layui-body').load(url);  
		})

		// 点击新增显示模态框
		$('#add').click(function(){
			//为了解决点击新增，inout框里有内容，所以所有的Input框里的value值都要置空
			$(".form-control").val("");//选中class为form-control的input框，使得value值置空
			$('#toAdd').show();
		})
		// 新增模态框关闭
		$('button:contains(取消)').on('click',function(){
			$('button[type=reset]').trigger('click');
				$('#toAdd').hide();
		})
		// 新增模态框保存
		$('button:contains(保存)').on('click',function(){
				$('#toAdd').hide();
				var url="customer/saveOrUpdateCustomer";
				var lmName=$("input[name=lmName]").val();//取客户的姓名
				var msName=$("input[name=msName]").val();//取客户经理的姓名
				var region=$("select[name=region] option:selected").val();//取地区中选中的option
				var level=$("select[name=level] option:selected").val();//取客户等级
				var addr=$("input[name=addr]").val();
				var zip=$("input[name=zip]").val();
				var tel=$("input[name=tel]").val();
				var fax=$("input[name=fax]").val();
				var website=$("input[name=website]").val();
				var licenceNo=$("input[name=licenceNo]").val();
				var chieftain=$("input[name=chieftain]").val();
				var bankroll=$("input[name=bankroll]").val();
				var turnover=$("input[name=turnover]").val();
				var bank=$("input[name=bank]").val();
				var bankAccount=$("input[name=bankAccount]").val();
				var localTaxNo=$("input[name=localTaxNo]").val();
				var nationalTaxNo=$("input[name=nationalTaxNo]").val();
				var data={
						custId:custId,
						custName:lmName,
						custManagerName:msName,
						custRegion:region,
						custLevelLabel:level,
						custAddr:addr,
						custZip:zip,
						custTel:tel,
						custFax:fax,
						custWebsite:website,
						custLicenceNo:licenceNo,
						custChieftain:chieftain,
						custBankroll:bankroll,
						custTurnover:turnover,
						custBank:bank,
						custBankAccount:bankAccount,
						custLocalTaxNo:localTaxNo,
						custNationalTaxNo:nationalTaxNo
				};
				$.post(url,data,function(data){
					alert(data);
					//模拟点击客户信息查询，因为新增之后要查询最新的客户信息才能显示出最新的客户信息
					//下面使用的是jQuery的后代选择器，选择index.jsp页面，class=layui-nav-item，后代为dd标签，包含客户信息查询的标签
					$(".layui-nav-item dd:contains('客户信息查询')").trigger('click');
				});//发送异步请求
		})	
		// 显示编辑模态框
		$('.first').click(function(){
			custId=$(this).attr("value");
			var url="customer/findCustomerById/"+custId;
			$.get(url,function(data){
				$("input[name=lmName]").val(data.custName);
				$("input[name=msName]").val(data.custManagerName);
				$("select[name=region]").val(data.custRegion);
				
				$("select[name=level]").val(data.custLevelLabel);
				$("input[name=addr]").val(data.custAddr);
				$("input[name=zip]").val(data.custZip);
				$("input[name=tel]").val(data.custTel);
				$("input[name=fax]").val(data.custFax);
				$("input[name=website]").val(data.custWebsite);
				$("input[name=licenceNo]").val(data.custLicenceNo);
				$("input[name=chieftain]").val(data.custChieftain);
				$("input[name=bankroll]").val(data.custBankroll);
				$("input[name=turnover]").val(data.custTurnover);
				$("input[name=bank]").val(data.custBank);
				$("input[name=bankAccount]").val(data.custBankAccount);
				$("input[name=localTaxNo]").val(data.custLocalTaxNo);
				$("input[name=nationalTaxNo]").val(data.custNationalTaxNo);
			});
			$('#toAdd').show();
		});
		// 显示删除模态框
		$('.deletee').click(function(){
			$('#deleteModal').show();
			custId =$(this).attr("value");//获取到当前删除客户的custID
		})
		// 关闭删除模态框
		$('.close').click(function(){
			custId=null;
			$('#deleteModal').hide();
		})
		// 关闭删除模态框
		$('.btn-default').click(function(){
			custId=null;
			$('#deleteModal').hide();
		})
		// 确定删除
		$('.btn-primary').click(function(){
			//点击确定，执行删除，发送异步请求，下面是ajax的异步请求
			var url="customer/deleteCustomerById/"+custId;//获取删除客户的custId
			$.get(url,function(data){//发送异步请求，按照url传给后台，删除后执行function方法，方法中传参data是后台cusdeleteCustomerById方法上加了一个@ResponseBody
				//注解，就会把方法返回的“删除成功”信息作为响应体传给前端
				alert(data);
				//模拟点击客户信息查询，因为删除成功后要点击客户信息查询，才能显示当前最新的客户信息
				$(".layui-nav-item dd:contains('客户信息查询')").trigger('click');
			});
			$('#deleteModal').hide();
		})
		
		// 查询
		$('.search').click(function(){
			//获取前端用户输入的客户名称、所在区域和等级
			var custName = $("input[name=custName]").val();
			var city = $("select[name=city]").val();
			var level = $("select[name=level]").val();
			//封装成对象
			var data = {
					custName:custName,	
					custRegion:city,
					custLevelLabel:level
			};	
			var url="customer/findCustomerLike/1"
			$(".layui-body").load(url,data);//参数data是前端输入的数据，传到后台，后台通过传入的数据进行查询
			//通过url找到处理的方法，处理完返回"customer/customer"，经过解析之后变成customer.jsp，把它加载到index.jsp页面的class=layui-body的标签处
		})
		
		//编辑联系人
		$(".user").click(function(){
			custId=$(this).attr("value");
			var url="linkman/findLinkmanByCustId/"+custId+"/1";
			$(".layui-body").load(url);
		});

	})
</script>
