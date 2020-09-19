package com.absinthe.anywhere_.ui.editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.absinthe.anywhere_.model.database.AnywhereEntity
import com.absinthe.anywhere_.ui.dialog.EXTRA_FROM_WORKFLOW

abstract class BaseEditorFragment : Fragment(), IEditor {

    protected val item by lazy { requireArguments().getParcelable(EXTRA_ENTITY) as? AnywhereEntity ?: AnywhereEntity.Builder() }
    protected val isEditMode by lazy { requireArguments().getBoolean(EXTRA_EDIT_MODE) }
    protected val isFromWorkflow by lazy { requireArguments().getBoolean(EXTRA_FROM_WORKFLOW) }
    protected var doneItem: AnywhereEntity = AnywhereEntity.Builder()

    protected abstract fun setBinding(inflater: LayoutInflater, container: ViewGroup?): View
    protected abstract fun initView()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = setBinding(inflater, container)
        initView()
        return root
    }

    override fun doneEdit(): Boolean {
        return if (isFromWorkflow) {
            EditorActivity.workflowResultItem.value = doneItem
            true
        } else {
            false
        }
    }
}