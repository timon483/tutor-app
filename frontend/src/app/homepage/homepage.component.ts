import { Component } from '@angular/core';
import {MatButton} from '@angular/material/button';
import {HeaderComponent} from '../header/header.component';
import {NgOptimizedImage} from '@angular/common';

@Component({
  selector: 'app-homepage',
  imports: [
    MatButton,
    HeaderComponent,
    NgOptimizedImage
  ],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css'
})
export class HomepageComponent {

}
