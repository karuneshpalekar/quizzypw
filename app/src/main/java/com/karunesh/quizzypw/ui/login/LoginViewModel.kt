package com.karunesh.quizzypw.ui.login


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val demoStudentMapping = mapOf(
        "SGGP782001" to "SG211"
    )

    fun loginWithSchoolAndStudentId(schoolId: String, studentId: String) {
        if (schoolId.isBlank() || studentId.isBlank()) {
            _error.value = "School ID and Student ID cannot be empty"
            return
        }

        val expectedStudentId = demoStudentMapping[schoolId]
        if (expectedStudentId == null || expectedStudentId != studentId) {
            _error.value = "Invalid School ID or Student ID"
            return
        }

        val email = "$schoolId@dummy.com"
        val password = schoolId

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _loginSuccess.value = true
                } else {
                    _error.value = task.exception?.message ?: "Login failed"
                }
            }
    }

    fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }
}