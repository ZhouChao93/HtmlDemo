package top.zcgyt.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zcgyt.bean.dto.QueryLocationDTO;
import top.zcgyt.bean.dto.UserLocationInfoDTO;
import top.zcgyt.bean.po.LocationInfoPO;
import top.zcgyt.bean.vo.PageVO;
import top.zcgyt.builder.PageBuilder;
import top.zcgyt.service.LocationService;
import top.zcgyt.valid.ValidGroup;

import javax.annotation.Resource;

/**
 * 位置信息
 *
 * @author zhouchao
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/location/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class LocationController {
	@Resource
	LocationService locationService;
	@Resource
	PageBuilder pageBuilder;

	/**
	 * 添加位置信息
	 *
	 * @param userLocationInfoDTO 用户位置信息
	 * @return 位置信息
	 * @since 1.0
	 */
	@PostMapping(value = "add")
	public Long add(@Validated(value = ValidGroup.NewAdd.class) @RequestBody UserLocationInfoDTO userLocationInfoDTO) {
		LocationInfoPO location = BeanUtil.copyProperties(userLocationInfoDTO, LocationInfoPO.class);
		return locationService.add(location);
	}

	/**
	 * 查询位置信息
	 *
	 * @param query 查询参数
	 * @return 带分页信息的位置信息
	 * @since 1.0
	 */
	@PostMapping(value = "list")
	public PageVO<LocationInfoPO> list(@Validated(value = ValidGroup.QueryPage.class) @RequestBody QueryLocationDTO query) {
		Page<LocationInfoPO> pageList = locationService.list(query);
		return pageBuilder.builder(pageList);
	}


}
