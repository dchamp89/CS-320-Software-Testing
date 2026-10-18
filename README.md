# CS 320: Software Testing, Automation, and Quality Assurance

Portfolio work from my CS 320 course at Southern New Hampshire University. This repository contains a mobile application back end with contact, task, and appointment services, fully tested with JUnit 5, along with my summary and reflections report.

## Contents

| Folder / File | Description |
|---|---|
| `ContactService/` | Contact.java, ContactService.java, and their JUnit tests |
| `TaskService/` | Task.java, TaskService.java, and their JUnit tests |
| `AppointmentService/` | Appointment.java, AppointmentService.java, and their JUnit tests |
| `Project Two Summary and Reflections Report.docx` | My report on my testing approach, techniques, and mindset |

**Testing results:** 84 unit tests with 100% code coverage of all six application classes.

## Reflection

### How can I ensure that my code, program, or software is functional and secure?

I make sure my code is functional by testing every requirement for both the right input and the wrong input, then checking the result instead of just confirming the code ran. Coverage tells me what code was reached, but only well written assertions tell me the code did what the user needed. For security, I never trust data after I have validated it. In my Appointment class, I store a copy of the date so nobody can change it later and skip my validation. I also test the exact boundaries of every input, because that is where both bugs and attackers tend to show up.

### How do I interpret user needs and incorporate them into a program?

I start by understanding what the customer needs as an end user, then break each requirement into its own group of tests. In this project, I labeled every test group with the requirement it covered, such as "Requirement 4: phone (required, exactly 10 digits)." That way, every rule the customer gave me is backed by tests that prove the program follows it.

### How do I approach designing software?

I design in small, self-contained pieces that are easy to test on their own. Each feature in this project has an object class that enforces its rules and a service class that manages it, with a HashMap for fast lookups by ID. I write tests alongside the code instead of saving them for the end, and I question my own assumptions as much as anyone else's. Working in aerospace has shown me that cutting corners on quality is never worth it, and that discipline applies to software just as much as hardware.
