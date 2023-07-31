# Orders list
This app consists of a single screen displaying fake orders.
Orders are received from server.
Query example (you can copy-paste this into your browser):

    https://externalwebhooks.dostavista.net/mobile-interview-api.php?since_id=42010&limit=10

Server returns random data, but the following will always be true:

1. order_id values are sequential
2. If since_id is specified, first item id will be equal to since_id + 1

## Task 1
Change displayed order date format to *September, 15 2023*

## Task 2
Add paging. 
When you scroll down to the end of the list, app should automatically start loading next page.
While next page is loading, loading indicator must be displayed as the last item of the list.