//mtype = {on, off, false, true, empty, full, low};

mtype fire_alarm_power = on;
mtype fire_alarm_state = off;
mtype fire_alarm_battary = full;
mtype smoke_detected = false;
mtype firefighters_called = false;
mtype battary_state = inUse;

proctype alarm() {
  do
    :: (fire_alarm_power == on && smoke_detected == false) ->
      printf("Fire alarm is powered on and no smoke detected.\n");
      fire_alarm_state = off;
    :: (fire_alarm_power == on && smoke_detected == true) ->
      printf("System detect smoke, alarm turns on
        and firefighters is called\n");
      fire_alarm_state = on;
      firefighters_called = true;
    :: (fire_alarm_state == on && smoke_detected == false) ->
      printf("Alarm turned off");
      fire_alarm_state = off;
    :: (fire_alarm_power == off) ->
      printf("Fire alarm is not turned on so it can't detect any smoke\n");
    :: (fire_alarm_battary == empty) ->
      printf("Battary of fire alarm is empty so it turned off\n");
      fire_alarm_power = off;
    :: (fire_alarm_battary == low) ->
      printf("Fire alarm battary charge is low - long 'beeps' is coming out\n");
  od
}

proctype human() {
  do
    :: (fire_alarm_power == off && (fire_alarm_battary == full
      || fire_alarm_battary == low ) ->
        printf("Fire alarm turned on\n");
        fire_alarm_power = on;
        battary_state = inUse;
    :: (fire_alarm_battary == low || fire_alarm_battary == empty) ->
      printf("Battary is changed\n");
      fire_alarm_battary = full;
      battary_state = changed;
  od
}


ltl p1 { fire_alarm_battary -> <> low }
ltl p2 { fire_alarm_battary -> <> (low U empty) }
ltl p3 { fire_alarm_battary -> <> (empty U changed) }
ltl p4 { smoke_detected -> false }

init {
  run alarm();
  run human();
}
