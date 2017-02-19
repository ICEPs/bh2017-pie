class DonationsController < ApplicationController
  before_action :set_donations, only: [:show, :update, :destroy]

  def index
    @donations = Donation.all

    if params[:filter]
      case params[:filter]

      when "name"
        render json: get_by_name
      when "company_name"
        render json: get_by_organization_name
      when "urgency"
        render json: get_by_urgency
      when "post_author"
        render json: get_by_author
      end
    else
      render json: @donations
    end
  end

  def create
    @donation = Donation.new
    @donation.message = params[:message]
    @donation.organization_name = params[:organization_name]
    @donation.urgency = params[:urgency]
    @donation.post_author_id = params[:post_author_id]
    @donation.donation_title = params[:donation_title]

    if @donation.save
      render json: @donation, status: :created, location: @donation
    else
      render json: @donation.errors, status: :unprocessable_entity
    end
  end

  def show
    render json: @donations
  end

  def update
    @donation = Deliverable.find(params[:id])

    if @donation.update(donation_params)
      head :no_content
    else
      render json: @donation.errors, status: :unprocessable_entity
    end
  end

  def destroy
    @donation.destroy

    head :no_content
  end



  private

    def set_donations
      @donations = Donations.find(params[:id])
    end

    def donation_params
      params.require(:donation).permit!
    end

    def get_by_name
      Donations.order(:donation_name)
    end

    def get_by_organization_name
      Donations.order(:organization_name)
    end

    def get_by_urgency
      Donations.order(:urgency)
    end

    def get_by_author
      Donations.order(:post_author_id)
    end

end
