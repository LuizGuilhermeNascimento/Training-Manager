import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserJson } from 'src/app/models/login.models';
import { keys } from 'src/app/services/local-storage/keys.json';
import { LocalStorageService } from 'src/app/services/local-storage/localstorage.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent {

  user: UserJson;
  constructor(private router: Router, private localStorageService: LocalStorageService) {
    this.user = {
      id: this.localStorageService.getItem<string>(keys.idKey) ?? '',
      role: this.localStorageService.getItem<number>(keys.roleKey) ?? 0
    }
  }

  
}
