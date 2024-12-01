import pandas as pd

def game_analysis(activity: pd.DataFrame) -> pd.DataFrame:
    # Ensure the event_date column is in datetime format
    activity['event_date'] = pd.to_datetime(activity['event_date'])
    
    # Group by player_id and find the minimum event_date
    result = activity.groupby('player_id', as_index=False).agg(first_login=('event_date', 'min'))
    
    return result
