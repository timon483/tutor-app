import { Component } from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {first} from 'rxjs';
import {MatButton} from '@angular/material/button';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-signup-form',
  imports: [ReactiveFormsModule, MatButton, RouterLink],
  templateUrl: './signup-form.component.html',
  styleUrl: './signup-form.component.css'
})
export class SignupFormComponent {

  signUpform = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    city: new FormControl(''),
    index: new FormControl(''),
    dateOfBirth: new FormControl(''),
    email: new FormControl(''),
    hourRate: new FormControl(''),
    subjects: new FormControl('')
    }

  )


  onSubmit() {
    // TODO: Use EventEmitter with form value
    console.warn(this.signUpform.value);
  }


  protected readonly first = first;
}
