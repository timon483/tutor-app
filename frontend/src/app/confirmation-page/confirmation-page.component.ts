import { Component } from '@angular/core';
import {NgOptimizedImage} from "@angular/common";
import {MatButton} from '@angular/material/button';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-confirmation-page',
  imports: [
    NgOptimizedImage,
    MatButton,
    RouterLink
  ],
  templateUrl: './confirmation-page.component.html',
  styleUrl: './confirmation-page.component.scss'
})
export class ConfirmationPageComponent {

}
