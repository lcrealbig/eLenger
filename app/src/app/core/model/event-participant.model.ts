import { User } from "./user.model";

export interface EventParticipant {
  id: string;                    
  event: Event;                  
  user: User;                  
  score?: number | null;         
  rank?: number | null;         
  joinedAt: Date | string;      
}