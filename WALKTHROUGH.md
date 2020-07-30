# Walkthrough

This is quick site walkthrough for testing functionality:

- Visit https://warm-hamlet-22515.herokuapp.com/
- At the Home Page, visit About Page.
- Visit Contact Page.
- Visit the Error Page by appending "/error" to the URL. You should see no trace stack.  Click on the "Return to Home Page" button.
- Attempt to visit a non-existent page by appending "/lost" to the URL. You should be redirected to the Login page.
- Attempt to visit Admin Pages.
- At the Login page, click "Cancel" in the warning. You should be redirected to UMGC.
- Click the back button and click on the Login link.
- Acknowledge the warning by clicking "OK", then click on the Log in button. It should not let you log in.
- Attempt to enter nonsense text or fake credentials. You should receive warnings and errors.
- Click on the "Create a Letter of Life Account" button.
- Enter "<script>" (without the quotes) in the Last Name textbox, "P@ssW0rd" in the Password textbox, and "I don't know" in the Security Question text box. Click on "Register". You should receive warnings and errors.
- Enter a set of credentials. DO NOT SELECT ANY ALLERGIES, CONDITIONS, OR MEDICATIONS. I have a known bug where the page returns strings in set format which need to be converted into actual set types.

  - Archer
  - Sterling
  - 03/17/1980
  - 159357258
  - 1 Main St
  - Salisbury
  - MD
  - 21801
  - 4108675309
  - Lana Kane
  - 4105555555
  - 3
  - Dr. Krieger
  - 4105555555
  - ISIS
  - A159357258
  - Checked
  - DNR
  - Allergies
  - Conditions
  - Medications
  - This is a test.
  - sarcher@vlol.gov
  - P@ssW0rd
  - Who is my father?
  - I don't know

- Click on "Register"; you will be redirected to the Login page.
- Enter your credentials and the Log in button.
- Visit the Error Page by appending "/error" to the URL. You should now see a trace stack with details about the error.  Click on the "Return to Home Page" button.
- Click on Admin.
- Click on Allergy management.
- View an Allergy and then return to the allergy list.
- Edit an allergy and save. Re-edit the allergy by returning it to its original value and save.
- Add an allergy (e.g., "Bees", etc.) and save.
- Do a full search for "be", view the allergy, and return to the allergy list.
- Do a table search for "ine", view one of the results, and return to the allergy list.
- Sort the table by Allergy name by selecting the up-and-down arrows in the table heading.
- Attempt to delete "Bees". Click on "Cancel"; nothing shuold be deleted.
- Attempt to delete "Bees" again. Click on "OK"; "Bees" shuold be deleted.
- Click on "Print"; a print dialog should appear. KNOWN BUG - The Actions column is printed or exported as well. Close the print dialog.
- Click on Return to Administration Menu.
- Repeat the process with each Management Page. KNOWN BUG - DO NOT SELECT ANY ALLERGIES, CONDITIONS, OR MEDICATIONS when adding or editing a User. I have a known bug where the page returns strings in set format which need to be converted into actual set types.
- Print out https://github.com/garciart/SWEN670/blob/master/VLOL/qr_code_testpage.pdf or open it on a different device.
- Click on Capture QR Code.
- Select the appropriate camera and hold a QR code to the camera. You should be redirected to the user's profile.
- If possible, attempt to use a QR code that is not on the testpage. You should be redirected to the Error page with a full stack trace.
- Click on the "Return to Home Page" button.
- Log out.

Thanks.