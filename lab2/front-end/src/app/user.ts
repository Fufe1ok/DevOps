import { Car } from "./car";
import { Fare } from "./fare";
import { Role } from './role';
export class User {
    userId: number;
    userFirstName: string;
    userLastName: string;
    userEmail: string;
    userPassword: string;
    userRole: Role;
    userCars: Car[];
    userFares: Fare[];
    createdAt: string;
    updatedAt: string;
}
