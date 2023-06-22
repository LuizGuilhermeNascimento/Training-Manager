import { TestBed } from '@angular/core/testing';

import { AuthServiceService } from './auth-service.service';
import { keys } from '../local-storage/keys.json';

describe('AuthServiceService', () => {
  let service: AuthServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should be false', () => {
    expect(service.isLoggedIn()).toBeFalsy();
  });

  it('should be true', () => {
    localStorage.setItem(keys.idKey, JSON.stringify(1));
    localStorage.setItem(keys.roleKey, JSON.stringify(1));
    expect(service.isLoggedIn()).toBeTrue();
    localStorage.clear();
  });
});
