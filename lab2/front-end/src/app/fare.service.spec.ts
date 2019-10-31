import { TestBed } from '@angular/core/testing';

import { FareService } from './fare.service';

describe('BeaconService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FareService = TestBed.get(FareService);
    expect(service).toBeTruthy();
  });
});
