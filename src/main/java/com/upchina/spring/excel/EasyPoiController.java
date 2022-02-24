package com.upchina.spring.excel;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.upchina.spring.common.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.apiguardian.api.API;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by anjunli on  2022/1/5
 **/
@Service
@Api(tags = "EasyPoiController", description = "EasyPoi导入导出测试")
@RequestMapping("/easypoi")
public class EasyPoiController {

    @ApiOperation("从excel中导入数据")
    @RequestMapping(value = "/importMemberList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult importMemberList(@RequestPart("file") MultipartFile file) {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        try {
            List<Member> list = ExcelImportUtil.importExcel(
                    file.getInputStream(),
                    Member.class, params
            );
            System.out.println(list.get(1));
            return CommonResult.success("导入成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.error("导入失败", -1);
        }
    }

    @ApiOperation("导出会员列表excel")
    @GetMapping("/exportMemberList")
    public void exportMemberList(ModelMap map, HttpServletRequest request, HttpServletResponse response) {
        List<Member> memberList = new ArrayList<>();
        Member member = new Member(1L, "admin", "123456", "Admin", new Date(), "13423675948", null, 0);
        Member member2 = new Member(2L, "admin2", "1234562", "Admin2", new Date(), "13423675948", null, 0);
        memberList.add(member);
        memberList.add(member2);

        ExportParams params = new ExportParams("会员列表", "会员列表", ExcelType.XSSF);
        map.put(NormalExcelConstants.DATA_LIST, memberList);
        map.put(NormalExcelConstants.CLASS, Member.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put(NormalExcelConstants.FILE_NAME, "memberList");
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    @ApiOperation("导出会员列表excel")
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<Map<String, Object>> sheetsList = new ArrayList<>();

        List<Member> memberList = new ArrayList<>();

        Member member = new Member(1L, "admin", "123456", "Admin", new Date(), "13423675948", null, 0);
        Member member2 = new Member(2L, "admin2", "1234562", "Admin2", new Date(), "13423675948", null, 0);
        memberList.add(member);
        memberList.add(member2);
        ExportParams params = new ExportParams();
        params.setSheetName("用户留痕信息");

        //创建sheet使用的map
        HashMap<String, Object> map1 = new HashMap<>();
        // title的参数为ExportParams类型
        map1.put("title", params);
        // 模版导出对应得实体类型
        map1.put("entity", Member.class);
        // sheet中要填充得数据
        map1.put("data", memberList);

        sheetsList.add(map1);
        Workbook workbook = ExcelExportUtil.exportExcel(sheetsList, ExcelType.HSSF);

        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("用户协议留痕表.xls", "UTF-8"));
        response.setCharacterEncoding("UTF-8");
        workbook.write(response.getOutputStream());
    }
}

