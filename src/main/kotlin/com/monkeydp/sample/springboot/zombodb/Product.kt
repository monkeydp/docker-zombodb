package com.monkeydp.sample.springboot.zombodb

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

/**
 * @author iPotato-Work
 * @date 2021/3/18
 */
@Entity
@Table(name = "products")
class Product(
        var name: String,
        var shortSummary: String = "<简介>",
        var longDescription: String = "<描述>",
        var price: Int = 0,
        var inventory_count: Int = 0,
        var discontinued: Boolean = false,
        val availability_date: Date = Date(),
) {
    @Id
    @GeneratedValue
    val id: Long = -1
}

@Repository
interface ProductRepo : JpaRepository<Product, Long> {
    @Query("SELECT zdb.score(ctid),* FROM products WHERE products ==> CONCAT('name:',:name)", nativeQuery = true)
    fun findAllByName(name: String): List<Product>

    @Query("SELECT zdb.score(ctid),* FROM products WHERE products ==> CONCAT('name:',:name) ORDER BY score DESC", nativeQuery = true)
    fun findAllByNameOrderByScore(name: String): List<Product>
}

@Api(tags = ["产品"])
@RestController
@RequestMapping("products")
class ProductController(
        private val repo: ProductRepo,
) {
    @ApiOperation("产品列表")
    @GetMapping
    fun list(form: ListForm) =
            repo.findAllByName(form.name)

    class ListForm(val name: String)

    @ApiOperation("产品创建")
    @PostMapping
    fun create(@RequestBody form: CreateForm) =
            repo.save(Product(form.name))

    class CreateForm(val name: String)

    @ApiOperation("产品编辑")
    @PutMapping
    fun edit(@RequestBody form: EditForm) =
            form.run {
                repo.findById(id).get()
                        .also { it.name = name }
                        .run(repo::save)
            }

    class EditForm(
            val id: Long,
            val name: String,
    )

    @ApiOperation("产品删除")
    @DeleteMapping
    fun delete(@RequestBody form: DeleteForm) =
            repo.deleteById(form.id)

    class DeleteForm(val id: Long)
}
