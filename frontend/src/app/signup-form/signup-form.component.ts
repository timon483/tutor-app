import {Component, inject, signal} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {first} from 'rxjs';
import {MatButton} from '@angular/material/button';
import {RouterLink} from '@angular/router';
import {MatFormField, MatLabel} from '@angular/material/form-field';
import {MatChipEditedEvent, MatChipGrid, MatChipInput, MatChipInputEvent, MatChipRow} from '@angular/material/chips';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {LiveAnnouncer} from '@angular/cdk/a11y';
import {MatIcon} from '@angular/material/icon';

@Component({
  selector: 'app-signup-form',
  imports: [ReactiveFormsModule,
    MatButton,
    RouterLink,
    MatFormField,
    MatChipGrid,
    MatChipRow,
    MatIcon,
    MatChipInput,
    MatLabel],
  templateUrl: './signup-form.component.html',
  styleUrl: './signup-form.component.scss'
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

  readonly addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  readonly subjects = signal<Subject[]>([{name: 'English'}, {name: 'German'}, {name: 'Math'}]);
  readonly announcer = inject(LiveAnnouncer);

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    // Add our fruit
    if (value) {
      this.subjects.update(subjects => [...subjects, {name: value}]);
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  remove(fruit: Subject): void {
    this.subjects.update(subjects => {
      const index = subjects.indexOf(fruit);
      if (index < 0) {
        return subjects;
      }

      subjects.splice(index, 1);
      // @ts-ignore
      this.announcer.announce(`Removed ${fruit.name}`);
      return [...subjects];
    });
  }

  edit(fruit: Subject, event: MatChipEditedEvent) {
    const value = event.value.trim();

    // Remove fruit if it no longer has a name
    if (!value) {
      this.remove(fruit);
      return;
    }

    // Edit existing fruit
    this.subjects.update(subjects => {
      const index = subjects.indexOf(fruit);
      if (index >= 0) {
        subjects[index].name = value;
        return [...subjects];
      }
      return subjects;
    });
  }




  onSubmit() {
    // TODO: Use EventEmitter with form value
    console.warn(this.signUpform.value);
  }


  protected readonly first = first;
}

export interface Subject {
  name: string;
}
