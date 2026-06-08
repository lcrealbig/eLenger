import { Group } from "./group.model";
import { User } from "./user.model";

export interface GroupMember {
  id: string;                          // UUID
  group: Group | string;               // pełny obiekt grupy lub ID
  user: User;                          // użytkownik
  role: GroupRole;                     // rola w grupie
  points: number;                      // punkty zdobyte w grupie
  joinedAt: Date | string;             // data dołączenia
}

export enum GroupRole {
  OWNER = 'OWNER',      // właściciel (twórca grupy)
  ADMIN = 'ADMIN',      // administrator (może zarządzać członkami)
  MEMBER = 'MEMBER'     // zwykły członek
}