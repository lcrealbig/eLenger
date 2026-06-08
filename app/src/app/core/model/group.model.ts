import { GroupMember } from "./group-member.model";
import { User } from "./user.model";

export interface Group {
  id: string;                        
  name: string;
  description?: string;               
  owner: User;                      
  isPublic: boolean;
  members: GroupMember[];        
  createdAt: Date | string;
  updatedAt: Date | string;
}
