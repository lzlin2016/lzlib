package com.lib.lzlin.api.utils.deviceUtils;

import com.lib.lzlin.api.utils.commonUtils.CommonUtils;

/**
 * 项目名称: Lib-lz
 * <p>
 * 类的描述: 文件管理器
 * 创建人: Administrator
 * 创建时间:  2017/4/15 10:23
 * 修改人: lz - Administrator
 * 修改备注:
 */

public class FileManager {
	// TODO 　根据实际需要, 修改成当前公司名
	public static String getSaveFilePath() {
		if (CommonUtils.hasSDCard()) {
			return CommonUtils.getRootFilePath() + "ibenhong/files/";
		} else {
			return CommonUtils.getRootFilePath() + "ibenhong/files";
		}
	}
	
	public static String getSaveImagePath() {
		if (CommonUtils.hasSDCard()) {
			return CommonUtils.getRootFilePath() + "ibenhong/images/";
		} else {
			return CommonUtils.getRootFilePath() + "ibenhong/images";
		}
	}
	
	public static String getSaveRatePath() {
		if (CommonUtils.hasSDCard()) {
			return CommonUtils.getRootFilePath() + "ibenhong/";
		} else {
			return CommonUtils.getRootFilePath() + "ibenhong";
		}
	}
}
