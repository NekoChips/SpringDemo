package com.demo.elastic.repository;

import com.demo.elastic.bean.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author NekoChips
 * @description Employee Repository
 * @date 2020/4/22
 */
public interface EmployeeRepository extends ElasticsearchRepository<Employee, Integer> {

    /**
     * 查询 LastName 满足要求的结果, ES 中执行如下语句：
     * GET /spring-elastic-demo/employee/_search
     * {
     *   "query": {
     *     "match": {
     *       "lastName": lastName
     *     }
     *   }
     * }
     * @param lastName lastName 的值
     * @return list
     */
    List<Employee> findAllByLastName(String lastName);

    /**
     * 查询 About 满足要求的结果， About 字段支持分词， ES 中执行如下语句：
     * GET /spring-elastic-demo/employee/_search
     * {
     *   "query": {
     *     "match": {
     *       "about": about
     *     }
     *   }
     * }
     * @param about about 值
     * @return list
     */
    List<Employee> findEmployeesByAbout(String about);

    /**
     * 复合查询 LastName 满足要求且 Age 的值大于 age 的结果集， ES 中执行如下语句：
     * GET /spring-elastic-demo/employee/_search
     * {
     *   "query": {
     *     "bool": {
     *       "must": [
     *         {
     *           "match": {
     *             "lastName": lastName
     *           }
     *         }
     *       ],
     *       "filter": {
     *         "range": {
     *           "age": {
     *             "gt": age
     *           }
     *         }
     *       }
     *     }
     *   }
     * }
     * @param lastName lastName的值
     * @param age age的值
     * @return list
     */
    List<Employee> findEmployeesByLastNameAndAgeAfter(String lastName, Integer age);
}
