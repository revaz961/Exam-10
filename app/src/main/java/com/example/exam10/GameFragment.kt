package com.example.exam10

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.exam10.databinding.GameFragmentBinding

class GameFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()
    private var _binding: GameFragmentBinding? = null
    private val binding
        get() = _binding!!

    private var items = mutableListOf<MutableList<Int?>>()
    private lateinit var adapter: GameAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_binding == null) {
            _binding = GameFragmentBinding.inflate(layoutInflater)
            init()
        }
        return binding.root
    }

    private fun init() {
        initList()
        initRecycler()
    }

    private fun initList() {
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, null).shuffled()
        items.add(mutableListOf<Int?>())
        items.add(mutableListOf<Int?>())
        items.add(mutableListOf<Int?>())
        for (i in list.indices) {
            items[i / 3].add(list[i])
        }
    }

    private fun initRecycler() {
        adapter = GameAdapter().apply {
            setItems(toOneDimensionList(items))

            click = {
                move(it)
            }
        }
        binding.rvBoard.adapter = adapter
        binding.rvBoard.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    private fun toOneDimensionList(list: MutableList<MutableList<Int?>>): MutableList<Int?> {
        val oneDimension = mutableListOf<Int?>()
        for (i in 0 until list.size)
            oneDimension.addAll(list[i])
        return oneDimension
    }

    private fun move(position: Int) {

        val i = position / 3
        val j = position % 3

        if (i > 0 && items[i - 1][j] == null) {
            items[i - 1][j] = items[i][j]
            items[i][j] = null
        }

        if (j > 0 && items[i][j - 1] == null) {
            items[i][j - 1] = items[i][j]
            items[i][j] = null
        }

        if (i < items.size - 1 && items[i + 1][j] == null) {
            items[i + 1][j] = items[i][j]
            items[i][j] = null
        }

        if (j < items[0].size - 1 && items[i][j + 1] == null) {
            items[i][j + 1] = items[i][j]
            items[i][j] = null
        }

        adapter.setItems(toOneDimensionList(items))

        if (checkWin())
            findNavController().navigate(R.id.action_gameFragment_to_winFragment)
    }

    private fun checkWin():Boolean{
        val list = toOneDimensionList(items)
        for(i in 0 .. list.size - 2)
            if(i+1 == list[i])
                return false
        return true
    }
}