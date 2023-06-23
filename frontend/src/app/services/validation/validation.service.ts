import { Injectable } from '@angular/core';
import { LocalStorageService } from '../local-storage/localstorage.service';
import { keys } from '../local-storage/keys.json';

@Injectable({
  providedIn: 'root',
})
export class ValidationService {
  constructor(private localStorageService: LocalStorageService) {}

  isLoggedIn(): boolean {
    return (
      this.localStorageService.getItem<number>(keys.roleKey) !== null &&
      this.localStorageService.getItem<string>(keys.idKey) !== null
    );
  }
}
