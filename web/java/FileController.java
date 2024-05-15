package top.zcgyt.controller;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.zcgyt.bean.FileResultVO;
import top.zcgyt.service.file.FileService;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * 文件相关接口
 *
 * @author zhouchao
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/file/")
public class FileController {
	@Resource
	FileService fileService;

	@Value("${config.upload.remoteSavePath:F:\\tmp_code}")
	private String uploadFilePath;

	@Value("${config.upload.uploadFileReadPre}")
	private String fileReadPath;

	private static final List<String> supportFileFormats = new ArrayList<>(Arrays.asList("doc,docx,xls,xlsx,ppt,pptx,pdf,jpg,jpeg,png,txt,wmv,mp4".split(",")));


	/**
	 * 文件上传接口
	 *
	 * @param files 文件信息
	 * @return 文件上传结果
	 * @since 1.0
	 */
	@PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FileResultVO upload(@RequestPart("files") MultipartFile[] files) {
		for (MultipartFile file : files) {
			String name = file.getOriginalFilename();
			if (StringUtils.isNotBlank(name)) {
				String fileSuffix = name.substring(name.lastIndexOf(".") + 1);
				boolean check = checkFileType(fileSuffix);
				if (check) {
					String fileName = UUID.randomUUID().toString().replace("-", "") + "." + fileSuffix;
					String newPath = uploadFilePath + File.separator + fileName;
					log.debug("上传文件保存文件至[{}]", newPath);
					File saveFile = new File(newPath);
					try {
						file.transferTo(saveFile);
						return new FileResultVO(fileReadPath + fileName, true, "文件上传成功");
					} catch (IOException | IllegalStateException e) {
						log.error("文件复制出现异常", e);
						return new FileResultVO(null, false, "文件上传失败,请联系管理员处理");
					}
				} else {
					return new FileResultVO(null, false, "文件格式不支持，现在只支持" + supportFileFormats);
				}
			} else {
				return new FileResultVO(null, false, "文件名不能为空");
			}

		}
		return new FileResultVO(null, false, "没有接收到文件");
	}


	/**
	 * 文件下载接口
	 *
	 * @param filePath  指定的文件目录
	 * @param inline    是否需要在线预览
	 * @param userAgent 浏览器user-agent
	 * @return 文件流信息
	 * @throws IOException 处理异常
	 */
	@RequestMapping("download")
	public ResponseEntity<byte[]> download(@RequestParam(value = "filePath", required = false) String filePath, @RequestParam(value = "inline", required = false) Boolean inline, @RequestHeader("user-agent") String userAgent) throws IOException {
		File file = fileService.parseFile(filePath);
		ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.ok();
		bodyBuilder.body(FileUtil.readBytes(file));
		bodyBuilder.contentLength(file.length());
		// 二进制数据流
		bodyBuilder.contentType(MediaType.APPLICATION_OCTET_STREAM);
		// 文件名编码
		String encodeFileName = URLEncoder.encode(file.getName(), "UTF-8");
		// IE
		if (userAgent.indexOf("MSIE") > 0) {
			bodyBuilder.header("Content-Disposition", "attachment;filename=" + encodeFileName);
		} else {
			// 其他浏览器
			if (Objects.nonNull(inline) && inline) {
				// 在浏览器中打开
				URL url = new URL("file:///" + file);
				bodyBuilder.header("Content-Type", url.openConnection().getContentType());
				bodyBuilder.header("Content-Disposition", "inline;filename*=UTF-8''" + encodeFileName);
			} else {
				// 直接下载
				bodyBuilder.header("Content-Disposition", "attachment;filename*=UTF-8''" + encodeFileName);
			}
		}
		// 下载成功返回二进制流
		return bodyBuilder.build();
	}


	/**
	 * 检查文件类型是否是指定的类型
	 *
	 * @param fileSuffix 文件类型
	 * @return true支持的文件类型  false不支持的文件类型
	 */
	private boolean checkFileType(String fileSuffix) {
		if (!StringUtils.isNotBlank(fileSuffix)) {
			return false;
		}
		return supportFileFormats.stream().anyMatch(fileSuffix::equals);
	}

}
