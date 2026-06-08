export interface Identity {
  id: string;
  email: string;
  emailVerified?: boolean;
  provider?: string;                  
  providerId?: string;
}