package com.monkeydp.sample.springboot.zombodb

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
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
        @Id
        @GeneratedValue
        val id: Long,
        var name: String,
        var shortSummary: String,
        var longDescription: String,
        var price: Int,
        var inventory_count: Int,
        var discontinued: Boolean,
        val availability_date: Date,
) {
    var score: String? = null
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
class ProductController(
        private val repo: ProductRepo,
) {
    @ApiOperation("产品列表")
    @PostMapping("list")
    fun list(form: ListForm) =
            form.run {
                repo.findAllByName(name)
            }

    class ListForm(
            val name: String,
    )
}
