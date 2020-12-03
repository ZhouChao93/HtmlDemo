package cn.chinacti.crm.admin.action;

import cn.chinacti.crm.admin.entity.TaskCallUserInfo;
import cn.chinacti.crm.admin.service.ITaskCallUserService;
import cn.chinacti.crm.common.action.BaseAction;
import cn.chinacti.crm.util.PageBean;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author zc
 */
@Controller("taskCallUserInfoAction")
@Scope(value = "prototype")
public class TaskCallUserInfoAction extends BaseAction<TaskCallUserInfo> {

	private Logger logger = Logger.getLogger(TaskCallUserInfoAction.class);

	@Autowired
	private ITaskCallUserService taskCallUserService;
	@Setter@Getter
	private Integer curr;
	@Setter@Getter
	private Integer nums;


	@Setter@Getter
	private TaskCallUserInfo taskCallUserInfo;

	/**
	 * 列表页
	 */
	public String listView() {


		return SUCCESS;
	}

	/**
	 * 列表页接口
	 */
	public String listData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int pageNum = curr;
		int pageSize = nums;
		List<Object> param = new ArrayList<>();
		StringBuilder hql = new StringBuilder("FROM TaskCallUserInfo where 1 = 1 ");
		if(null!=taskCallUserInfo){
			if(StringUtils.isNotBlank(taskCallUserInfo.getUserName())){
				hql.append(" and  userName  like ?");
				param.add("%"+taskCallUserInfo.getUserName()+"%");
			}
			if(StringUtils.isNotBlank(taskCallUserInfo.getUserphone())){
				hql.append(" and  userphone  like ?");
				param.add("%"+taskCallUserInfo.getUserphone()+"%");
			}
		}
		PageBean<TaskCallUserInfo> pageBeanOfHql = taskCallUserService.findPageBeanOfHql(hql.toString(), pageNum, pageSize, param.toArray());
		map.put("data",pageBeanOfHql.getRecordList());
		map.put("code","1");
		map.put("count",pageBeanOfHql.getRecordCount());
		map.put("msg","查询成功");
		return JSON;
	}

	/**
	 * 添加或更新接口
	 */
	public String addOrUpdate() {
		try {
			if(null==taskCallUserInfo||taskCallUserInfo.getTaskUserId()==null){
				taskCallUserInfo = taskCallUserInfo==null?new TaskCallUserInfo():taskCallUserInfo;
				taskCallUserInfo.setCreateTime(new Date());
				taskCallUserInfo.setCreateUser(getOperator().getId());
				taskCallUserInfo.setDataType(0);
				taskCallUserService.add(taskCallUserInfo);
			}else {
				taskCallUserInfo.setUpdateTime(new Date());
				TaskCallUserInfo info = taskCallUserService.findById(taskCallUserInfo.getTaskUserId());
				info.setUpdateTime(new Date());
				info.setUpdateUser(getOperator().getId());
				info.setUserphone(taskCallUserInfo.getUserphone());
				taskCallUserService.update(info);
			}
			map.put("code","1");
			map.put("msg","操作成功");
		}catch (Exception e){
			map.put("code","-1");
			map.put("msg","操作失败");
			e.printStackTrace();
		}
		return JSON;
	}

	/**
	 * 依据ID删除数据,支持批量删除
	 */
	public String dels(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String ids = request.getParameter("ids");
			String[] split = ids.split(",");
			List<String> idList = new ArrayList<>(Arrays.asList(split));
			taskCallUserService.deleteByIds("task_calluser_info","task_user_id",idList);
			map.put("code","1");
			map.put("msg","操作成功");
		}catch (Exception e){
			map.put("code","-1");
			map.put("msg","操作失败");
			e.printStackTrace();
		}
		return JSON;
	}


}
