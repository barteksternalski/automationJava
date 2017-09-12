Feature: User management
  As u user I want to have possibility to view, create, edit and delete users

#USR-02
  Scenario Outline: Successfully create new user
    Given User is on main page
    When User creates new user with given data
    #	req				req				req				opt					req			req			opt			req
    #					[---ORGANIZATION INFORMATION - when user type is Organization---]
      | userType          | <userType>	  |
      | CSIONetID	      | <csioNetId>	  |
      | PartnerType	      | <partnerType> |
      | OrganizationName  | <orgName>	  |
      | Logo		      | <logo>	      |
      | Name		      | <name>	      |
      | Email		      | <email>	      |
      | Modules	          | <modules>	  |
    Then User is created


    Examples:
      | userType		| csioNetId		| partnerType	| orgName	        | logo		| name		| email		| modules	|
      | temp			| temp			| temp			| temp				| temp		| temp		| temp		| temp		|

#USR-02
  Scenario Outline: Error handling during new user creation
    Given User is on main page
    When User creates new user with given data
    #	req				req				req				opt					req			req			opt			req
      | userType          | <userType>	  |
      | CSIONetID	      | <csioNetId>	  |
      | PartnerType	      | <partnerType> |
      | OrganizationName  | <orgName>	  |
      | Logo		      | <logo>	      |
      | Name		      | <name>	      |
      | Email		      | <email>	      |
      | Modules	          | <modules>	  |
    Then Proper error message <message> is displayed

    Examples:
      | userType		| csioNetId		| partnerType	| orgName	        | logo		| name		| email		| modules	| message								|
      | {null}		    | 				|				|					|			|			|			|			| ???									|
      | 				| {null}		|				|					|			|			|			|			| Please enter CSIONet ID				|
      | 				| 				| {null}		|					|			|			|			|			| ???									|
      | 				| 				|				|					| {null}	|			|			|			| ???									|
      | 				| 				|				|					| 			| {null}	|			|			| Please enter last name				|
      | 				| 				|				|					| 			|			|			| {null}	| ???									|
      | 				| 				|				|					| 			|			| kokos 	| 			| Please enter a valid email address	|

#USR-01
  Scenario Outline: View user details
    Given <user> is on user listing page
    When User selects listed user <type>
    Then User details are displayed

    Examples:
      | user		| type			|
      | CSIOAdmin	| CSIOAdmin		|
      | CSIOAdmin	| Organization	|
      | CSIOAdmin	| Regular		|
      | Regular	    | Regular		|

#USR-04
  Scenario: Activate user
    Given User is on user listing page
    When User activates listed user
    Then User is activated

#USR-04
  Scenario: Deactivate user
    Given User is on user listing page
    When User deactivates listed user
    Then User is deactivated

  Scenario: User can reset password
    Given User is on user listing page
    When User resets password for selected user
    Then New password is sent to given email

  Scenario: User can be deleted
    Given User is on user listing page
    When User deletes selected user
    Then User is no longer listed