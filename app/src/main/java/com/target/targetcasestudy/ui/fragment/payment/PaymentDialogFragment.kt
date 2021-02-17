package com.target.targetcasestudy.ui.fragment.payment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.validateCreditCard

/**
 * Dialog that displays a minimal credit card entry form.
 *
 * Your task here is to enable the "submit" button when the credit card number is valid and
 * disable the button when the credit card number is not valid.
 *
 * You do not need to input any of your actual credit card information. See `Validators.kt` for
 * info to help you get fake credit card numbers.
 *
 * You do not need to make any changes to the layout of this screen (though you are welcome to do
 * so if you wish).
 */
class PaymentDialogFragment : DialogFragment(), TextWatcher {

    private lateinit var submitButton: Button
    private lateinit var creditCardInput: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.dialog_payment, container, false)

        submitButton = root.findViewById(R.id.submit)
        creditCardInput = root.findViewById(R.id.card_number)
        val cancelButton: Button = root.findViewById(R.id.cancel)

        cancelButton.setOnClickListener { dismiss() }
        submitButton.isEnabled = false

        creditCardInput.addTextChangedListener(this)
        creditCardInput.setOnKeyListener { _, _, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_UP) {
                validateCardAndEnableButton()
            }
            false
        }

        return root
    }

    override fun afterTextChanged(cardNumber: Editable?) {
        validateCardAndEnableButton()
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(cardNumber: CharSequence?, start: Int, before: Int, count: Int) {
    }

    private fun validateCardAndEnableButton() {
        val cardNumber = creditCardInput.text.toString()
        if (validateCreditCard(cardNumber)) {
            submitButton.isEnabled = true
            submitButton.setOnClickListener { dismiss() }
        } else {
            submitButton.isEnabled = false
        }
    }

}