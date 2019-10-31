import { User } from './user';

export class Fare {
    fareId: number;
    fareUuid: string;
    fareStatus: string;
    fareUser: User;
    createdAt: string;
    updatedAt: string;
}