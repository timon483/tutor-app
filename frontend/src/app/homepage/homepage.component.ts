import { Component } from '@angular/core';
import {MatButton} from '@angular/material/button';
import {HeaderComponent} from '../header/header.component';

@Component({
  selector: 'app-homepage',
  imports: [
    MatButton,
    HeaderComponent
  ],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css'
})
export class HomepageComponent {

}
