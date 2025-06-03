package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.example.anno.Log;
import org.example.pojo.Dept;
import org.example.pojo.Result;
import org.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询部门列表
     * @return
     */
    //@RequestMapping(value = "/depts",method = RequestMethod.GET) //method:指定请求方式
    @GetMapping
    public Result list() {
        //System.out.println("查询全部部门数据");
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /**
     * 删除部门
     */
    //方式一：基于HttpServletRequest对象获取参数
    /*@DeleteMapping
    public Result delete(HttpServletRequest request){
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        System.out.println("删除部门："+id);
        return Result.success();
    }*/
    //方式二：通过 @RequestParam 注解将请求参数绑定给方法形参
    /*@DeleteMapping
    public Result delete(@RequestParam (value = "id",required = false) Integer deptId ){
        System.out.println("删除部门："+ deptId);
        return Result.success();
    }*/
    //方式三：如果请求参数名与形参变量名相同，直接定义方法形参即可接收。（省略@RequestParam）
    @Log
    @DeleteMapping
    public Result delete(Integer id ){
        //System.out.println("删除部门："+ id);
        log.info("删除部门：{}",  id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        //System.out.println("新增部门：" + dept);
        log.info("新增部门：{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据ID查询部门
     */
    /*@GetMapping("/{id}")
    public Result getInfo(@PathVariable("id") Integer deptId){
        System.out.println("根据id查询部门：" + deptId);
        return Result.success();
    }*/
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        //System.out.println("根据id查询部门：" + id);
        log.info("根据id查询部门：{}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门信息
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        //System.out.println("修改部门：" + dept);
        log.info("修改部门：{}", dept);
        deptService.update(dept);
        return Result.success();
    }

}
