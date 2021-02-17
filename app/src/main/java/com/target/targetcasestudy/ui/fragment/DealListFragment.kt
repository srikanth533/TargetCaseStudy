package com.target.targetcasestudy.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.R
import com.target.targetcasestudy.repository.DealsRepository
import com.target.targetcasestudy.repository.model.Result
import com.target.targetcasestudy.ui.adapter.DealItemAdapter
import com.target.targetcasestudy.viewmodel.DealsViewModel
import com.target.targetcasestudy.viewmodel.DealsViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class DealListFragment : Fragment(), Observer<Result> {

    @Inject
    lateinit var repository: DealsRepository

    private lateinit var dealItemAdapter: DealItemAdapter
    private lateinit var viewModel: DealsViewModel
    private lateinit var viewModelFactory: DealsViewModelFactory
    private lateinit var recyclerView: RecyclerView

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_deal_list, container, false)

        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(activity)
        dealItemAdapter = DealItemAdapter(activity)
        recyclerView.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            layoutManager.orientation
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.adapter = dealItemAdapter

        viewModelFactory = DealsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DealsViewModel::class.java)

        viewModel.fetchDeals().observe(this, this)

        return view
    }

    override fun onChanged(result: Result?) {
        result?.let {
            when (it) {
                is Result.Success -> {
                    val responseList = it.productList
                    if (responseList.isNullOrEmpty()) {
                        Toast.makeText(
                            activity?.applicationContext,
                            "No results for deals!!",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        dealItemAdapter.setData(responseList)
                        dealItemAdapter.notifyDataSetChanged()
                    }
                }

                is Result.Error -> {
                    Toast.makeText(
                        activity?.applicationContext,
                        it.exception.errorMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}
