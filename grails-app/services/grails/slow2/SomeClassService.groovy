package grails.slow2

import slow.SomeClass;

import grails.transaction.Transactional

@Transactional
class SomeClassService {

    def serviceMethod(params, max) {
        def x = System.currentTimeMillis()
        SomeClass.findAllByNameLike('%')
        //SomeClass.workaround.list()
        //SomeClass.withCriteria { like('name', '%') }
        SomeClass.list()
        //new groovy.sql.Sql(dataSource).rows("select * from SOME_CLASS where name like '%'")
        params.max = Math.min(max ?: 10, 100)
        def res = [someClassInstanceList: SomeClass.list(params), someClassInstanceTotal: SomeClass.count()]
        println("T = ${System.currentTimeMillis() - x}ms....")
        return res
    }
}
