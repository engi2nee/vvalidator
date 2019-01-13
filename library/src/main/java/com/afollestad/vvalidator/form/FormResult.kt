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
@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.afollestad.vvalidator.form

import com.afollestad.vvalidator.field.FieldError
import com.afollestad.vvalidator.field.FieldResult

/**
 * Holds the validation result of a whole form.
 *
 * @author Aidan Follestad (@afollestad)
 */
class FormResult {
  private val errors = mutableListOf<FieldError>()

  /** Returns true if validation passed with no errors. */
  fun success() = errors.isEmpty()

  /** Returns true if validation failed with errors. */
  fun hasErrors() = errors.isNotEmpty()

  /** Returns errors that occurred during validation. */
  fun errors(): List<FieldError> = errors

  /** Merges a [FieldResult] into this result. */
  operator fun plusAssign(fieldResult: FieldResult) {
    errors.addAll(fieldResult.errors())
  }
}
