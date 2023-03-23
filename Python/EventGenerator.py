import json
import random
import string
import sys

def generate_event(event_template, index, live=False):
# define the variable which used to distinguish live or non-live
    live_str = "live" if live else "non-live"
    # define the name of event template
    event_template['name'] = f'World Cup UPL Test {index} {live_str}'
    time_window = event_template['timeWindow']
    if live:
        time_window['eventValidEndTime'] =  "Nov 21 2022 00:01:00"
        time_window['landingPageEndTime'] =  "Nov 21 2022 23:59:00"
    else:
        time_window['eventValidEndTime'] =  "Nov 02 2022 00:01:00"
        time_window['landingPageEndTime'] =  "Nov 02 2022 23:59:00"

    # define the localized slots
    localized_slots = event_template["requestCriteria"]["localizedSlots"]

    for locale in ['en_US', 'en_CA', 'es_US', 'es_MX', 'pt_BR']:
        # creating object for the specific locale
        slot_groups = {}
        slot_groups['VideoName'] = gen_random_strings(40)
        slot_groups['Event'] = gen_random_strings(12)
        slot_groups['Sport'] = gen_random_strings(12)
        slot_groups['League'] = gen_random_strings(12)
        slot_groups['SportsTeam'] = gen_random_strings(12)
        slot_groups['SportsTeamOpponent'] = gen_random_strings(12)
        localized_slots[locale] = slot_groups

    # create the new slots and added to specific area in the json file
    # made sure the ascii is false
    return json.dumps(event_template, ensure_ascii=False)

# not fully understand this part
def gen_random_strings(num):
    # A List of Strings
    strings = []
    for i in range(0, num):
        # '' represent an empty string
        strings.append(''.join(random.choices(string.ascii_uppercase +
                             string.digits, k=8))) # 8 average string length
    return strings

def main():
    # run the main function
    if len(sys.argv) != 4:
        print(f'Usage: {sys.argv[0]} <json file output path> <event live copies> <event non-live copies>')
        exit(0)

    # as we open the json file and encode it as utf-8
    f = open("event-template.json", encoding='utf-8')
    event_template = json.load(f)
    f.close

    # create a big object for final usage
    final_object = []
    for live_num in range(1, int(sys.argv[2]) + 1):
        final_object.append(generate_event(event_template, live_num, live=True))
    for nonlive_num in range(1, int(sys.argv[3]) + 1):
        final_object.append(generate_event(event_template, nonlive_num, live=False))

    # not too sure about this part yet.
    # "w" write mode; "r" read mode
    with open(sys.argv[1], "w", encoding='utf-8') as outfile:
        json.dump(final_object, outfile, sort_keys=True, ensure_ascii=False, indent=4)

if __name__ == "__main__":
    main()