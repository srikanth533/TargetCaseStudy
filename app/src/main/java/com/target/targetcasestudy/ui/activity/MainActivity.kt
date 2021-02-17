package com.target.targetcasestudy.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.target.targetcasestudy.R
import com.target.targetcasestudy.repository.remote.model.Product
import com.target.targetcasestudy.ui.Constants
import com.target.targetcasestudy.ui.fragment.DealItemFragment
import com.target.targetcasestudy.ui.fragment.DealListFragment
import com.target.targetcasestudy.ui.fragment.payment.PaymentDialogFragment

class MainActivity : AppCompatActivity(), DealItemListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                DealListFragment()
            )
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.credit_card -> {
                PaymentDialogFragment().show(supportFragmentManager, "CreditCardValidation")
                true
            }
            else -> false
        }
    }

    override fun onItemClicked(product: Product) {
        val dealItemFragment = DealItemFragment()
        val bundle = Bundle()
        bundle.putParcelable(Constants.PRODUCT_KEY, product)
        dealItemFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                dealItemFragment
            ).addToBackStack("DealItemFragment")
            .commit()
    }
}

interface DealItemListener {

    fun onItemClicked(product: Product)
}
