package com.yisu.easyexcel.read.excellistener;

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.yisu.easyexcel.read.entity.SysUser;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserExcelListener extends AnalysisEventListener<SysUser> {

    /**
     * 批处理阈值
     */
    private static final int BATCH_COUNT = 5;
    List<SysUser> list = new ArrayList<>(BATCH_COUNT);

    @Override
    public void invoke(SysUser sysUser, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSONUtil.toJsonStr(sysUser));
        list.add(sysUser);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        log.info("所有数据解析完成！");
    }

    private void saveData(){
        log.info("{}条数据，开始存储数据库！", list.size());
        log.info("存储数据库成功！");
    }
}
