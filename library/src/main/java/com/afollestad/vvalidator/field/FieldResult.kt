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
@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.afollestad.vvalidator.field

import androidx.annotation.IdRes

/** @author Aidan Follestad (@afollestad) */
data class FieldError(
  /** The view ID that the error is for. */
  @IdRes val id: Int,
  /** The name of the field that the error is for. */
  val name: String,
  /** The description of the error. */
  val description: String
) {
  /** A combination of [name] and [description] to display to a user. */
  override fun toString(): String {
    return "$name $description"
  }
}

/**
 * Holds the validation result of a single field.
 *
 * @author Aidan Follestad (@afollestad)
 */
class FieldResult {
  private val errors = mutableListOf<FieldError>()

  /** Returns true if validation passed with no errors. */
  fun success() = errors.isEmpty()

  /** Returns true if validation failed with errors. */
  fun hasErrors() = errors.isNotEmpty()

  /** Returns errors that occurred during validation. */
  fun errors(): List<FieldError> = errors

  /** Appends an error to the validation result. */
  internal fun addError(error: FieldError) = errors.add(error)

  override fun toString(): String {
    return if (success()) {
      "Success"
    } else {
      "${errors.size} errors"
    }
  }
}
