package com.szu.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gene.dao.GeneDao;
import com.gene.dao.GeneEntity;

import java.net.URLDecoder;
import java.util.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@SpringBootApplication
public class DemoApplication {

    private final Logger log = LoggerFactory.getLogger(DemoApplication.class);


    @Autowired
    private weixin_log weixin_log;

    @Autowired
    private GeneDao gd;


    @RequestMapping(value="/",method = RequestMethod.GET)
    public String index(){
        log.info("------request reveive------");
        //return weixin_log.getHost()+":"+weixin_log.getPort();

        //List<GeneEntity> list = gd.findAll();

        //return list.get(0).getName();

        //RestController 返回视图页面
        //ModelAndView mv = new ModelAndView("index");
        //return mv;
        return "index";
    }

    @RequestMapping(value="/analyse",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String analyse(@RequestBody(required = false) String jsob_str) throws Exception{

        if(jsob_str == null || jsob_str == ""){
            //参数异常
            log.info("基因序列参数异常");
        }
        else{
            log.info(jsob_str);
        }

        JSONObject jsob = JSONObject.parseObject(jsob_str);
        //获取用户输入的基因序列及过滤参数

        String max_length = jsob.get("max_length").toString();
        String min_group = jsob.get("min_group").toString();
        String loop_size_s = jsob.get("loop_size_s").toString();
        String loop_size_e = jsob.get("loop_size_e").toString();
        String gene_sequence = jsob.get("gene_sequence").toString();

        //参数判断：是否全为“ATGC”字母组合
        if(!gene_sequence.matches("^[ATGC]+$")){
            //返回403
        }

        //调用分析接口，返回json结果串
        JSONObject jsob_ret = new JSONObject();
        JSONArray jsob_qgrs_seq = new JSONArray();
        JSONObject jo = new JSONObject();

        //模拟数据
        for(int i=0;i<10;i++){
            jo = new JSONObject();
            jo.put("position",i+1);
            jo.put("length","39");
            jo.put("qgrs","GGGAAAGGGCCC");
            jo.put("score","58");
            jsob_qgrs_seq.add(jo);
        }


        jsob_ret.put("data_view",jsob_qgrs_seq);
        jsob_ret.put("data_view_overlaps",JSONArray.parse(jsob_qgrs_seq.toJSONString()));

        jsob_ret.put("seq_length",gene_sequence.length());//序列长度
        jsob_ret.put("qgrs_found",2);//QGRS发现
        jsob_ret.put("qgrs_found_overlaps",126);//QGRS（包括重叠）

        //log.info(jsob_ret.toJSONString());

        return jsob_ret.toJSONString();
    }

    @RequestMapping(value="/analyse/geneID",method = RequestMethod.POST)
    @ResponseBody
    public String analyse_by_geneID(@RequestBody(required = false) String jsob_str){
        log.info("json参数"+jsob_str);
        if(jsob_str == null || jsob_str == ""){
            //参数异常
            log.info("基因ID参数异常");
        }
        else{
            log.info(jsob_str);
        }
        JSONObject jsob = JSONObject.parseObject(jsob_str);
        String geneID = jsob.getString("geneID");

        //定义返回串
        JSONObject jsob_ret = new JSONObject();

        log.info("查询基于ID = "+geneID);
        try{
            GeneEntity ge = gd.findById(Integer.parseInt(geneID));

            if(ge != null){
                jsob_ret.put("ID",ge.getId());
                jsob_ret.put("gene_name",ge.getName());
                jsob_ret.put("gene_des",ge.getDescribetion());
            }
            else{
                //匹配不到
                jsob_ret.put("ID","");
                jsob_ret.put("gene_name","");
                jsob_ret.put("gene_des","");
            }
        }
        catch(Exception e){
            e.printStackTrace();

        }

        return jsob_ret.toJSONString();

    }
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
