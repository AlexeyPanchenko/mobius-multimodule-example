package ru.alexeypanchenko.mobuisdonor.add

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.add_fragment.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.alexeypanchenko.mobuisdonor.add.dependencies.AddItemRepository
import ru.alexeypanchenko.mobuisdonor.add.di.AddItemComponentProvider
import javax.inject.Inject

class AddFragment : Fragment() {

    @Inject
    lateinit var repository: AddItemRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as AddItemComponentProvider).addItemComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.add_fragment, container, false)
        view.save_button.setOnClickListener {
            val title: String = view.title_edit.text.toString()
            val description: String = view.description_edit.text.toString()
            val additionalText: String = view.additional_edit.text.toString()
            if (title.isEmpty() || description.isEmpty()) {
                return@setOnClickListener
            }
            CoroutineScope(Dispatchers.Main).launch {
                val dialog = ProgressDialog(requireContext()).apply {
                    setMessage("Saving")
                }
                dialog.show()
                withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                    repository.addItem(AddItem(title, description, additionalText))
                }
                dialog.dismiss()
                requireFragmentManager().popBackStack()
            }

        }
        return view
    }
}