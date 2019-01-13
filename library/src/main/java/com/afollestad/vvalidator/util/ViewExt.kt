/**
 * Designed and developed by Aidan Follestad (@afollestad)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@file:Suppress("unused")

package com.afollestad.vvalidator.util

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import androidx.annotation.IntRange

fun EditText.onTextChanged(
  @IntRange(from = 0, to = 10000) debounce: Int = 0,
  cb: (String) -> Unit
) {
  addTextChangedListener(object : TextWatcher {
    val callbackRunner = Runnable {
      cb(text.trim().toString())
    }

    override fun afterTextChanged(s: Editable?) = Unit

    override fun beforeTextChanged(
      s: CharSequence,
      start: Int,
      count: Int,
      after: Int
    ) = Unit

    override fun onTextChanged(
      s: CharSequence,
      start: Int,
      before: Int,
      count: Int
    ) {
      removeCallbacks(callbackRunner)
      if (debounce == 0) {
        callbackRunner.run()
      } else {
        postDelayed(callbackRunner, debounce.toLong())
      }
    }
  })
}

fun Spinner.onItemSelected(cb: (Int) -> Unit) {
  onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) = Unit

    override fun onItemSelected(
      parent: AdapterView<*>?,
      view: View?,
      position: Int,
      id: Long
    ) = cb(position)
  }
}

fun View.show() {
  visibility = VISIBLE
}

fun View.hide() {
  visibility = GONE
}

fun View.showOrHide(show: Boolean) = if (show) show() else hide()
