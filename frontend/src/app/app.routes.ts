import { Routes } from '@angular/router';
import {SignupFormComponent} from './signup-form/signup-form.component';
import {HomepageComponent} from './homepage/homepage.component';
import {ConfirmationPageComponent} from './confirmation-page/confirmation-page.component';

export const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomepageComponent},
  {path: 'signup', component: SignupFormComponent},
  {path: 'confirmation', component: ConfirmationPageComponent}
];
