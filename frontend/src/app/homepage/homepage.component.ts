import { Component } from '@angular/core';
import {MatButton} from '@angular/material/button';
import {HeaderComponent} from '../header/header.component';
import {NgOptimizedImage} from '@angular/common';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-homepage',
  imports: [
    MatButton,
    HeaderComponent,
    NgOptimizedImage,
    RouterLink
  ],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.scss'
})
export class HomepageComponent {

}
