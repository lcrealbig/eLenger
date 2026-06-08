import { EventParticipant } from "./event-participant.model";
import { Group } from "./group.model";
import { User } from "./user.model";

export interface ChallangeModel {
  id: string;                   
  title: string;
  description?: string;          
  location?: string;             
  eventDate: Date | string;      
  owner: User;                   
  group?: Group;               
  isPublic: boolean;
  status: EventStatus;
  participants: EventParticipant[];
  createdAt: Date | string;
  updatedAt: Date | string;
}

export enum EventStatus {
  UPCOMING = 'UPCOMING',
  ACTIVE = 'ACTIVE', 
  FINISHED = 'FINISHED',
  CANCELLED = 'CANCELLED'
}